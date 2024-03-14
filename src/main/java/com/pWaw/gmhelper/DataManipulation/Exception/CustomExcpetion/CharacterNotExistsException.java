package com.pWaw.gmhelper.DataManipulation.Exception.CustomExcpetion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CharacterNotExistsException extends Exception {
    public CharacterNotExistsException(String message) {
        super(message);
    }
}
