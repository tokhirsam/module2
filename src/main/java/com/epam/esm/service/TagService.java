package com.epam.esm.service;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.payload.ApiResponse;

import java.util.List;

public interface TagService {

    ApiResponse getTagById(Long id);
    List <Tag> getAllTags();
    ApiResponse deleteTag(Long id);
    ApiResponse updateTag(Tag tag,Long id);
    ApiResponse createTag(Tag tag);
    Tag getTagByName(String tagName);
}
