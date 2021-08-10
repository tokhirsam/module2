package com.epam.esm.controller;

import com.epam.esm.enums.HttpStatus;
import com.epam.esm.model.Tag;
import com.epam.esm.payload.ApiResponse;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Long id) {
        ApiResponse tagById = tagService.getTagById(id);
        return ResponseEntity.status(tagById.getCodes().equals(HttpStatus.OK_200)?
                200 : 409).body(tagById);
    }

    @GetMapping
    public HttpEntity<?> getAll() {

        List<Tag> oneById = tagService.getAllTags();
        return ResponseEntity.ok(oneById);
    }
    @PostMapping
    public HttpEntity<?> addTag(@RequestBody Tag tag){
        ApiResponse add = tagService.createTag(tag);
        return ResponseEntity.status(add.getCodes().equals(HttpStatus.CREATED_201)?
                201 : 409).body(add);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> update( @RequestBody Tag tag, @PathVariable Long id)  {
        ApiResponse apiResponse = tagService.updateTag(tag, id);
       return ResponseEntity.status(apiResponse.getCodes().equals(HttpStatus.OK_200)?
               200 : 404).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id)  {
        ApiResponse apiResponse = tagService.deleteTag(id);
        return ResponseEntity.status(apiResponse.getCodes().equals(HttpStatus.OK_200)?
                200 : 404).body(apiResponse);
    }
}
