package com.pWaw.gmhelper.DataManipulation.Controller;

import com.pWaw.gmhelper.DataManipulation.Controller.Swagger.ImageController;
import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDetails;
import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDto;
import com.pWaw.gmhelper.DataManipulation.Exception.EmptyFileSendException;
import com.pWaw.gmhelper.DataManipulation.Exception.ImageNotExistsException;
import com.pWaw.gmhelper.DataManipulation.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageControllerImpl implements ImageController {

    private final ImageService imageService;

    @Override
    public ResponseEntity<ImageDetails> uploadImage(MultipartFile image) throws EmptyFileSendException {
        return new ResponseEntity<ImageDetails>(imageService.uploadImage(image), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ImageDetails> updateImage(Long id, MultipartFile image) throws ImageNotExistsException, EmptyFileSendException {
        return ResponseEntity.ok().body(imageService.updateImage(id, image));
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws ImageNotExistsException {
        ImageDto image = imageService.getImage(id);
        MediaType mediaType = MediaType.parseMediaType(image.getFileType());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return ResponseEntity.ok().headers(headers).body(image.getImageData());
    }

    @Override
    public ResponseEntity<Void> deleteImage(Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ImageDetails>> cacheImages(List<Long> idList) {
        return ResponseEntity.ok().body(imageService.preloadCache(idList));
    }

    @Override
    public ResponseEntity<List<ImageDetails>> getAllImagesInfo() {
        return ResponseEntity.ok().body(imageService.getAllImagesInfo());
    }
}
