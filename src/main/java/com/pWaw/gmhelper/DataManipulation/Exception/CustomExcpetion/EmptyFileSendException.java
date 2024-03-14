package com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmptyFileSendException extends Exception {
    public EmptyFileSendException(String message) {
        super(message);
    }
}
