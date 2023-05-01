package com.cgm.test.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {
    private String matrixId;
    private String name;
}
