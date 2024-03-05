package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDetails;

import com.pWaw.gmhelper.DataManipulation.Exception.EmptyFileSendException;
import com.pWaw.gmhelper.DataManipulation.Exception.ImageNotExistsException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping(ImageController.DataPath.ROOT)
public interface ImageController {

    class DataPath{
        private DataPath(){}
        public static final String ROOT = "api/gm-helper/data/image/";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ImageDetails> uploadImage(@RequestPart MultipartFile image) throws EmptyFileSendException;

    @PutMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ImageDetails> updateImage(@PathVariable Long id, @RequestPart MultipartFile image) throws ImageNotExistsException, EmptyFileSendException;

    @GetMapping("{id}")
    ResponseEntity<byte[]> getImage(@PathVariable Long id) throws ImageNotExistsException;

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteImage(@PathVariable Long id);

    @GetMapping("/list/{idList}")
    ResponseEntity<List<ImageDetails>> cacheImages(@PathVariable List<Long> idList);

    @GetMapping("list/details")
    ResponseEntity<List<ImageDetails>> getAllImagesInfo();
}
