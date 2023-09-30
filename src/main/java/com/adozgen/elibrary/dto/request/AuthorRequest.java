package com.adozgen.elibrary.dto.request;

import lombok.Data;

@Data
public class AuthorRequest {
    private String name;
    private Long zipcodeId;
}
