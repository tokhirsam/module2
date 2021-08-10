package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.enums.HttpStatus;
import com.epam.esm.model.Tag;
import com.epam.esm.payload.ApiResponse;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {


    private final TagDao tagDao;

    @Override
    public ApiResponse getTagById(Long id) {
        return tagDao.getTagById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.findAll();
    }

    @Override
    public ApiResponse deleteTag(Long id) {

        if (tagDao.deleteTag(id)) {
            return new ApiResponse("Tag deleted successfully", HttpStatus.OK_200);
        } else {
            return new ApiResponse("Tag ID not found: " + id, HttpStatus.NOT_FOUND_404);
        }

    }

    @Override
    public ApiResponse updateTag(Tag tag, Long id) {
        if (tagDao.updateTag(tag, id)) {
            return new ApiResponse("Tag updated successfully", HttpStatus.OK_200, tag);
        } else {
            return new ApiResponse("Tag ID not found: " + id, HttpStatus.NOT_FOUND_404);
        }
    }

    @Override
    public ApiResponse createTag(Tag tag) {
        try{
            Tag tagByName = tagDao.getTagByName(tag.getName());
            return new ApiResponse("Tag already exists", HttpStatus.CONFLICT_409, tagByName);
        }catch (Exception e){
            tagDao.createTag(tag);
            return new ApiResponse("Tag created successfully", HttpStatus.CREATED_201);
        }
    }

    @Override
    public Tag getTagByName(String tagName) {
        return tagDao.getTagByName(tagName);
    }
}
