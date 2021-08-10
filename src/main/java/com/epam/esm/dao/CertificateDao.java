package com.epam.esm.dao;


import com.epam.esm.model.GiftCertificate;
import com.epam.esm.dto.GiftCertificateDto;

import java.util.List;

public interface CertificateDao {

    GiftCertificate getCertificateById(Long id);
    List<GiftCertificate> getAllCertificates();
    Integer addCertificateReturningId(GiftCertificateDto dto);
    Integer findTagIdByName(String name);
    Integer insertToCertificate_Tags(Integer certificateId, Integer tagId);
    boolean deleteCertificateById(Long id);
    boolean deleteCertificateTagsById(Long id);
    boolean updateCertificate(GiftCertificateDto dto, Long id);
    List<GiftCertificate> getCertificateByTagName(String name);
    List<GiftCertificate> getCertificateBySorting();
    List<GiftCertificate> getCertificateBySortingNameDesc();
    List<GiftCertificate> getCertificateBySortingTime();
    List<GiftCertificate> getCertificateBySortingTimeDesc();
    List<GiftCertificate> getCertificateByLike(String param);
    List<GiftCertificate> getCertificateByLikeDescription(String param);
}
