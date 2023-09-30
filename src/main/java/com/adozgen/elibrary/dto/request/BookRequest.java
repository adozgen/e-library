package com.adozgen.elibrary.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private String name;
    private List<Long> authorIds;
    private Long categoryId;

}
