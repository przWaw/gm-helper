package com.pWaw.gmhelper.DataManipulation.Exception.ControllerAdvice;

import com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataManipulationExceptionHandler {

    @ExceptionHandler(EmptyFileSendException.class)
    ResponseEntity<ErrorMessage> handleImageReadingException(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorMessage message = ErrorMessage.builder()
                .errorCode(status)
                .className(e.getClass().getSimpleName())
                .errorMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(value = {
            CampaignNotExistsException.class,
            CharacterNotExistsException.class,
            ImageNotExistsException.class,
            NoteNotExistsException.class
    })
    ResponseEntity<ErrorMessage> handleResourceNotExistsException(Exception e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage message = ErrorMessage.builder()
                .errorCode(status)
                .className(e.getClass().getSimpleName())
                .errorMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(message, status);
    }

}
