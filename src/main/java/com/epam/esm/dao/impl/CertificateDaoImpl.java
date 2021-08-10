package com.epam.esm.dao.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.mappers.GiftCertificateMapper;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CertificateDaoImpl implements CertificateDao {

    private final JdbcTemplate jdbcTemplate;

    private static final String FIND_BY_ID = "select * from certificate where id = ?";
    private static final String FIND_ALL = "select * from certificate";
    private static final String INSERT_CERTIFICATE = "insert into certificate(name, " +
            "description, price, duration) values(?, ?, ?, ?) returning id";
    private static final String FIND_TAG_BY_ID = "select * from tags where id = ?";
    private static final String FIND_TAG_BY_NAME = "select id from tag where name = ?";
    private static final String INSERT_TO_CERTIFICATE_TAGS = "insert into certificates_tags " +
            "values (?,?)";
    private static final String DELETE_CERTIFICATE_BY_ID = "delete from certificate where id = ?";
    private static final String DELETE_CERTIFICATES_TAGS_BY_ID = "delete from certificates_tags where certificate_id = ?";
    private static final String UPDATE_CERTIFICATE = "update certificate set name = ?, description = ?," +
            " price = ?, duration = ? where id = ?";
    private static final String FIND_CERTIFICATE_BY_TAG_NAME = "select c.id, c.name, c.description, c.price, c.duration, c.create_date, c.last_update_date from certificate c join certificates_tags ct on c.id = " +
            "ct.certificate_id join tag t on t.id = ct.tag_id where t.name = ?";
    private static final String FIND_CERTIFICATE_SORTING_BY_ = "select * from certificate order by  name ";
    private static final String FIND_CERTIFICATE_SORTING_BY_NAME_DESC_ = "select * from certificate order by  name desc";
    private static final String FIND_CERTIFICATE_SORTING_BY_TIME_ = "select * from certificate order by  create_date ";
    private static final String FIND_CERTIFICATE_SORTING_BY_TIME_DESC_ = "select * from certificate order by  create_date desc";
    private static final String FIND_CERTIFICATE_BY_LIKE = "select * from certificate where name like ?";
    private static final String FIND_CERTIFICATE_BY_LIKE_DESC = "select * from certificate where description like ?";


    @Override
    public GiftCertificate getCertificateById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID,
                new Object[]{id}, new GiftCertificateMapper());
    }

    @Override
    public List<GiftCertificate> getAllCertificates() {
        return jdbcTemplate.query(FIND_ALL, new GiftCertificateMapper());
    }

    @Override
    public Integer addCertificateReturningId(GiftCertificateDto dto) {
        return jdbcTemplate.queryForObject(INSERT_CERTIFICATE, Integer.class,
                dto.getName(), dto.getDescription(), dto.getPrice(), dto.getDuration());
    }

    @Override
    public Integer findTagIdByName(String name) {
        Integer id = jdbcTemplate.queryForObject(FIND_TAG_BY_NAME, Integer.class, name);
        if (id>0)
            return id;
        else
            return 0;

    }

    @Override
    public Integer insertToCertificate_Tags(Integer certificateId, Integer tagId) {
        return jdbcTemplate.update(INSERT_TO_CERTIFICATE_TAGS,
                certificateId, tagId);
    }

    @Override
    public boolean deleteCertificateById(Long id) {
        return jdbcTemplate.update(DELETE_CERTIFICATE_BY_ID, id) > 0;
    }

    @Override
    public boolean deleteCertificateTagsById(Long id) {
        return jdbcTemplate.update(DELETE_CERTIFICATES_TAGS_BY_ID, id) > 0;
    }

    @Override
    public boolean updateCertificate(GiftCertificateDto dto, Long id) {
        return jdbcTemplate.update(UPDATE_CERTIFICATE, dto.getName(),
                dto.getDescription(), dto.getPrice(), dto.getDuration(), id) > 0;
    }

    @Override
    public List<GiftCertificate> getCertificateByTagName(String name) {
        return jdbcTemplate.query(FIND_CERTIFICATE_BY_TAG_NAME,
                new Object[]{name}, new GiftCertificateMapper());
    }

    @Override
    public List<GiftCertificate> getCertificateBySorting() {
        return jdbcTemplate.query(FIND_CERTIFICATE_SORTING_BY_,
                new GiftCertificateMapper());

    }

    @Override
    public List<GiftCertificate> getCertificateBySortingNameDesc() {
        return jdbcTemplate.query(FIND_CERTIFICATE_SORTING_BY_NAME_DESC_,
                new GiftCertificateMapper());
    }

    @Override
    public List<GiftCertificate> getCertificateBySortingTime() {
        return jdbcTemplate.query(FIND_CERTIFICATE_SORTING_BY_TIME_,
                new GiftCertificateMapper());

    }
    @Override
    public List<GiftCertificate> getCertificateBySortingTimeDesc() {
        return jdbcTemplate.query(FIND_CERTIFICATE_SORTING_BY_TIME_DESC_,
                new GiftCertificateMapper());

    }

    @Override
    public List<GiftCertificate> getCertificateByLike(String param) {
        return jdbcTemplate.query(FIND_CERTIFICATE_BY_LIKE,
                new Object[]{"%"+param+"%"}, new GiftCertificateMapper());

    }

    @Override
    public List<GiftCertificate> getCertificateByLikeDescription(String param) {
        return jdbcTemplate.query(FIND_CERTIFICATE_BY_LIKE_DESC,
                new Object[]{"%"+param+"%"}, new GiftCertificateMapper());

    }


}
