package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.ImageDetails;
import com.pWaw.gmhelper.DataManipulation.DTO.ImageDto;
import com.pWaw.gmhelper.DataManipulation.Exception.EmptyFileSendException;
import com.pWaw.gmhelper.DataManipulation.Exception.ImageNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.ImageMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Image;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;


    public ImageDetails uploadImage(MultipartFile image) throws EmptyFileSendException {
        ImageDto dto = ImageDto.readFromMultipart(image);
        Image imageToSave = imageMapper.dtoToImage(dto);
        return imageMapper.imageToDetails(imageRepository.save(imageToSave));
    }

    public ImageDetails updateImage(Long id, MultipartFile image) throws ImageNotExistsException, EmptyFileSendException {
        Optional<Image> imageToUpdate = imageRepository.findById(id);
        if(imageToUpdate.isEmpty()) {
            throw new ImageNotExistsException();
        }
        ImageDto dto = ImageDto.readFromMultipart(image);
        dto.setId(imageToUpdate.get().getId());
        Image imageToSave = imageMapper.dtoToImage(dto);
        return imageMapper.imageToDetails(imageRepository.save(imageToSave));
    }

    @Cacheable(value = "images", key = "#id")
    public ImageDto getImage(Long id) throws ImageNotExistsException {
        Optional<Image> image = imageRepository.findById(id);
        if(image.isEmpty()) {
            throw new ImageNotExistsException();
        }
        return imageMapper.imageToDto(image.get());
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public List<ImageDetails> getAllImagesInfo() {
        List<Image> images = imageRepository.findAll();
        return imageMapper.imageToDetails(images);
    }

    public void preloadCache(List<Long> ids) {
        List<Image> images = imageRepository.findAllByIdIn(ids);
        for(Image image : images) {
            cacheImage(image);
        }
    }

    @Cacheable(value = "images", key = "#image.id")
    private ImageDto cacheImage(Image image) {
        return imageMapper.imageToDto(image);
    }

}
