package com.pWaw.gmhelper.DataManipulation.Service;

import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDetails;
import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDto;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.EmptyFileSendException;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.ImageNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Mappers.ImageMapper;
import com.pWaw.gmhelper.DataManipulation.Model.Image;
import com.pWaw.gmhelper.DataManipulation.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "images")
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;


    public ImageDetails uploadImage(MultipartFile image) throws EmptyFileSendException {
        ImageDto dto = ImageDto.readFromMultipart(image);
        System.out.println(dto.getFileType());
        Image imageToSave = imageMapper.dtoToImage(dto);
        System.out.println(imageToSave.getFileType());
        return imageMapper.imageToDetails(imageRepository.save(imageToSave));
    }

    @CachePut(key = "#id")
    public ImageDetails updateImage(Long id, MultipartFile image) throws ImageNotExistsException, EmptyFileSendException {
        Optional<Image> imageToUpdate = imageRepository.findById(id);
        if(imageToUpdate.isEmpty()) {
            throw new ImageNotExistsException("Wrong id was provided during get operation, image does not exists");
        }
        ImageDto dto = ImageDto.readFromMultipart(image);
        dto.setId(imageToUpdate.get().getId());
        Image imageToSave = imageMapper.dtoToImage(dto);
        return imageMapper.imageToDetails(imageRepository.save(imageToSave));
    }

    @Cacheable(key = "#id")
    public ImageDto getImage(Long id) throws ImageNotExistsException {
        Optional<Image> image = imageRepository.findById(id);
        if(image.isEmpty()) {
            throw new ImageNotExistsException("Wrong id was provided during update operation, image does not exists");
        }
        return imageMapper.imageToDto(image.get());
    }

    @CacheEvict(key = "#id")
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public Page<ImageDetails> getAllImagesInfo(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return imageRepository.findAll(pageable).map(imageMapper::imageToDetails);
    }

    public List<ImageDetails> preloadCache(List<Long> ids) {
        List<Image> images = imageRepository.findAllByIdIn(ids);
        for(Image image : images) {
            cacheImage(image);
        }
        return imageMapper.imageToDetails(images);
    }

    @Cacheable(key = "#image.id")
    private ImageDto cacheImage(Image image) {
        return imageMapper.imageToDto(image);
    }

}
