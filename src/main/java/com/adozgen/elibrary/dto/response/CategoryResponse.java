package com.adozgen.elibrary.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private List<String> bookNames;
}
