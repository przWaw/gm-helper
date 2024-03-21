package com.pWaw.gmhelper.DataManipulation.Controller.Swagger;

import com.pWaw.gmhelper.DataManipulation.DTO.Image.ImageDetails;

import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.EmptyFileSendException;
import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.ImageNotExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping(ImageController.DataPath.ROOT)
@Tag(name = "Image Controller", description = "Class for image data manipulation.")
public interface ImageController {

    class DataPath{
        private DataPath(){}
        public static final String ROOT = "api/gm-helper/data/images/";
    }

    @Operation(summary = "Upload Image", description = "Upload file to create image in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Image uploaded"),
            @ApiResponse(responseCode = "400", description = "File was empty or corrupted therefore application"
                    + "was not able to create resource out of it")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ImageDetails> uploadImage(@RequestPart MultipartFile image) throws EmptyFileSendException;

    @Operation(summary = "Update image", description = "Put with MultipartFile and id in path to specify what image is updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image successfully updated"),
            @ApiResponse(responseCode = "404", description = "Image with given id was not found in database")
    })
    @PutMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ImageDetails> updateImage(@PathVariable Long id, @RequestPart MultipartFile image) throws ImageNotExistsException, EmptyFileSendException;

    @Operation(summary = "Get image", description = "Get image with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Image with given id was not found in database")
    })
    @GetMapping("{id}")
    ResponseEntity<byte[]> getImage(@PathVariable Long id) throws ImageNotExistsException;

    @Operation(summary = "Delete image", description = "Delete image with specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Image was successfully deleted")
    })
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteImage(@PathVariable Long id);

    @Operation(summary = "Cache images", description = "Preload images into cache for faster retrieval,"
            + " returns image details without actual image data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Images cached successfully"),
    })
    @GetMapping("{idList}")
    ResponseEntity<List<ImageDetails>> cacheImages(@PathVariable List<Long> idList);

    @Operation(summary = "Get all images information", description = "Get info about all images in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All images info retrieved successfully")
    })
    @GetMapping
    ResponseEntity<Page<ImageDetails>> getAllImagesInfo(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize);
}
