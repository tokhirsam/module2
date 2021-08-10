package com.epam.esm.service;

import com.epam.esm.model.GiftCertificate;
import com.epam.esm.payload.ApiResponse;
import com.epam.esm.dto.GiftCertificateDto;

import java.util.List;

public interface CertificateService {

    ApiResponse getCertificateById(Long id);
    List<GiftCertificate> getAllCertificates();
    ApiResponse addCertificate(GiftCertificateDto dto);
    ApiResponse deleteCertificate(Long id);
    ApiResponse updateCertificate(GiftCertificateDto dto, Long id);
    ApiResponse getAllCertificatesByTagName(String name);
    ApiResponse getAllCertificatesBySorting();
    ApiResponse getAllCertificatesBySortingNameDesc();
    ApiResponse getAllCertificatesBySortingTime();
    ApiResponse getAllCertificatesBySortingTimeDesc();
    ApiResponse getAllCertificatesByLikeName(String param);
    ApiResponse getAllCertificatesByLikeDescription(String param);
}
