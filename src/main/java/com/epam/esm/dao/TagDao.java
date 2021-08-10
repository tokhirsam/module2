package com.epam.esm.dao;

import com.epam.esm.model.Tag;
import com.epam.esm.payload.ApiResponse;

import java.util.List;

public interface TagDao {

    List<Tag> findAll();
    ApiResponse getTagById(Long id);
    boolean deleteTag(Long id);
    boolean updateTag(Tag tag,Long id);
    boolean createTag(Tag tag);
    Tag getTagByName(String name);
    List<Tag> getTagsByCertificateId(Long id);
    Integer addTagReturningId(String name);
}
