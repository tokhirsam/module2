package com.epam.esm.dao.mappers;


import com.epam.esm.model.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftCertificateMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int i) throws SQLException {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(resultSet.getLong("id"));
        giftCertificate.setName(resultSet.getString(2));
        giftCertificate.setDescription(resultSet.getString(3));
        giftCertificate.setPrice(resultSet.getInt(4));
        giftCertificate.setDuration(resultSet.getInt(5));
        giftCertificate.setCreate_date(resultSet.getString(6));
        giftCertificate.setLast_update_date(resultSet.getString(7));

        return giftCertificate;
    }

}
