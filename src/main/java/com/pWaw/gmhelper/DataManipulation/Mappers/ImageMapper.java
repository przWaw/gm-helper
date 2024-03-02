package com.pWaw.gmhelper.DataManipulation.Mappers;

import com.pWaw.gmhelper.DataManipulation.DTO.ImageDetails;
import com.pWaw.gmhelper.DataManipulation.DTO.ImageDto;
import com.pWaw.gmhelper.DataManipulation.Model.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto imageToDto(Image image);
    Image dtoToImage(ImageDto imageDto);
    ImageDetails imageToDetails(Image image);

    List<ImageDto> imageDto(List<Image> images);
    List<Image> dtoToImage(List<ImageDto> imageDtos);
    List<ImageDetails> imageToDetails(List<Image> images);
}
