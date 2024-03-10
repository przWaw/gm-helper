package com.pWaw.gmhelper.DataManipulation.DTO.Note;

import lombok.Data;

import java.util.List;

@Data
public class NoteDto {
    private Long id;
    private String content;
    private List<String> contentAbstract;
}
