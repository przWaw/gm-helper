package com.pWaw.gmhelper.DataManipulation.DTO.Note;

import lombok.Data;

import java.util.List;

@Data
public class NoteDto {
    private Long id;
    private String contentAbstract;
    private List<String> content;
}
