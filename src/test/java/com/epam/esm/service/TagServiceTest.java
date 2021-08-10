package com.epam.esm.service;

import com.epam.esm.dao.TagDao;
import com.epam.esm.model.Tag;
import com.epam.esm.payload.ApiResponse;
import com.epam.esm.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TagServiceTest {

    TagDao tagDaoMock = Mockito.mock(TagDao.class);
    TagService tagService = new TagServiceImpl(tagDaoMock);


    @Test
    void canGetTagById() {
        Tag tag = new Tag(1L, "name");
        ApiResponse apiResponse = new ApiResponse(null, null, tag);
        Mockito.when(tagDaoMock.getTagById(1L)).thenReturn(apiResponse);
        assertEquals(1L, tag.getId());
    }

    @Test
    void canGetAllTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.builder().build());
        tags.add(Tag.builder().build());
        Mockito.when(tagDaoMock.findAll()).thenReturn(tags);
        List<Tag> tagList = tagService.getAllTags();
        assertEquals(tags,tagList);
    }

    @Test
    void canDeleteTag() {
        Mockito.when(tagDaoMock.deleteTag(1L)).thenReturn(true);
        ApiResponse apiResponse = tagService.deleteTag(1L);
        assertEquals("Tag deleted successfully", apiResponse.getMessage());
    }

    @Test
    void canUpdateTag() {
        Tag tag = new Tag(1L, "name");
        Mockito.when(tagDaoMock.updateTag(tag,1L)).thenReturn(true);
        ApiResponse apiResponse = tagService.updateTag(tag,1L);
        assertEquals("Tag updated successfully", apiResponse.getMessage());
    }

    @Test
    void canCreateTag() {
        Tag tag = new Tag(1L, "name");
        Mockito.when(tagDaoMock.createTag(tag)).thenReturn(true);
        ApiResponse apiResponse = tagService.createTag(tag);
        assertEquals("Tag already exists", apiResponse.getMessage());
    }

}