package com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoteNotExistsException extends Exception {
    public NoteNotExistsException(String message) {
        super(message);
    }
}
