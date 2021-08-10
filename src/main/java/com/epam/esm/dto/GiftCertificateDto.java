package com.epam.esm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCertificateDto {
    private String name;
    private String description;
    private Integer price;
    private Integer duration;
    private String[] tags;
}
