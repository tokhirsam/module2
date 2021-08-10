package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.mappers.TagMapper;
import com.epam.esm.enums.HttpStatus;
import com.epam.esm.model.Tag;
import com.epam.esm.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagDaoImpl implements TagDao {

    private final JdbcTemplate jdbcTemplate;

    private final static String SQL_FIND_TAG = "select * from tag where id = ?";
    private final static String SQL_DELETE_TAG = "delete from tag where id = ?";
    private final static String SQL_UPDATE_TAG = "update tag set name = ? where id = ?";
    private final static String SQL_GET_ALL = "select * from tag";
    private final static String SQL_INSERT_TAG = "insert into tag(name) values(?)";
    private final static String SQL_FIND_TAG_BY_NAME = "SELECT * FROM tag where name = ?";
    private final static String SQL_FIND_TAGS_BY_CERTIFICATE_ID = "select t.id, t.name from tag t join certificates_tags ct on t.id=ct." +
            "tag_id where ct.certificate_id = ?";
    private final static String INSERT_TAG_RETURNING_ID = "insert into tag(name) values (?) returning id";
    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new TagMapper());
    }

    @Override
    public ApiResponse getTagById(Long id) {
        try{
            Tag tag = jdbcTemplate.queryForObject(SQL_FIND_TAG,
                    new Object[]{id}, new TagMapper());
            return new ApiResponse("Tag fetched successfully ID = "+id,HttpStatus.OK_200,tag);
        }catch (Exception e){
            return new ApiResponse("Tag ID not found. ID = "+id, HttpStatus.CONFLICT_409);
        }

    }

    @Override
    public boolean deleteTag(Long id) {
        return jdbcTemplate.update(SQL_DELETE_TAG, id) > 0;
    }

    @Override
    public boolean updateTag(Tag tag, Long id) {
        return jdbcTemplate.update(SQL_UPDATE_TAG, tag.getName(),
                id) > 0;
    }


    @Override
    public boolean createTag(Tag tag) {
        return jdbcTemplate.update(SQL_INSERT_TAG,
                tag.getName()) > 0;
    }
    @Override
    public Tag getTagByName(String tagName){
        return jdbcTemplate.queryForObject(SQL_FIND_TAG_BY_NAME,
                new Object[]{tagName}, new TagMapper());
    }

    public List<Tag> getTagsByCertificateId(Long id){
        return jdbcTemplate.query(SQL_FIND_TAGS_BY_CERTIFICATE_ID,new Object[]{id},
                new TagMapper());
    }

    @Override
    public Integer addTagReturningId(String name){
        return jdbcTemplate.queryForObject(INSERT_TAG_RETURNING_ID, Integer.class, name);
    }

}
