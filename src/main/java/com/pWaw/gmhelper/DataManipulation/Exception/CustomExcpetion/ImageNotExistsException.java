package com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ImageNotExistsException extends Exception {
    public ImageNotExistsException(String message) {
        super(message);
    }
}
