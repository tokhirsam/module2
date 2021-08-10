package com.epam.esm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificate {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer duration;
    private String create_date;
    private String last_update_date;
    private List<Tag> tags = new ArrayList<>();

    public GiftCertificate(Long id, String name, String description, Integer price, Integer duration, String create_date, String last_update_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.create_date = create_date;
        this.last_update_date = last_update_date;
    }
}
