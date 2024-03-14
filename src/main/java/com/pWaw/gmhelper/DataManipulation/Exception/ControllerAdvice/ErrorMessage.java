package com.pWaw.gmhelper.DataManipulation.Exception.ControllerAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private String className;
    private String errorMessage;
    private HttpStatus errorCode;
}
