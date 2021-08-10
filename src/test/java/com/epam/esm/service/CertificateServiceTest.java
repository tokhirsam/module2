package com.epam.esm.service;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.payload.ApiResponse;
import com.epam.esm.service.impl.CertificateServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CertificateServiceTest {

    CertificateDao certificateDao = Mockito.mock(CertificateDao.class);
    TagDao tagDaoMock = Mockito.mock(TagDao.class);
    CertificateService certificateService = new CertificateServiceImpl(certificateDao,tagDaoMock);





    @Test
    void getCertificateById() {
        GiftCertificate giftCertificate = GiftCertificate.builder().build();
        Mockito.when(certificateDao.getCertificateById(1L)).thenReturn(giftCertificate);
        ApiResponse apiResponse = certificateService.getCertificateById(1L);
        assertEquals("Certificate fetched successfully ID = 1", apiResponse.getMessage());
    }

    @Test
    void getAllCertificates() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(GiftCertificate.builder().build());
        certificates.add(GiftCertificate.builder().build());
        Mockito.when(certificateDao.getAllCertificates()).thenReturn(certificates);
        List<GiftCertificate> giftCertificates = certificateService.getAllCertificates();
        assertEquals(certificates,giftCertificates);
    }

    @Test
    void addCertificate() {
       String[] tags = {"sport","apparel"};
        GiftCertificateDto giftCertificate  = GiftCertificateDto.builder().build();
        giftCertificate.setTags(tags);
        Mockito.when(certificateDao.addCertificateReturningId(giftCertificate)).thenReturn(1);
        ApiResponse apiResponse = certificateService.addCertificate(giftCertificate);
        assertEquals("New Certificate added successfully", apiResponse.getMessage());
    }

    @Test
    void deleteCertificate() {
        Mockito.when(certificateDao.deleteCertificateById(1L)).thenReturn(true);
        ApiResponse apiResponse = certificateService.deleteCertificate(1L);
        assertEquals("Certificate ID not found: 1", apiResponse.getMessage());
    }

    @Test
    void updateCertificate() {
        GiftCertificateDto giftCertificate  = GiftCertificateDto.builder().build();
        Mockito.when(certificateDao.updateCertificate(giftCertificate,1L)).thenReturn(true);
        ApiResponse apiResponse = certificateService.updateCertificate(giftCertificate,1L);
        assertEquals("Certificate ID not found: 1", apiResponse.getMessage());
    }



    @Test
    void getAllCertificatesBySorting() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(GiftCertificate.builder().build());
        certificates.add(GiftCertificate.builder().build());
        Mockito.when(certificateDao.getCertificateBySorting()).thenReturn(certificates);
        ApiResponse allCertificatesBySorting = certificateService.getAllCertificatesBySorting();
        assertEquals(certificates, allCertificatesBySorting.getObject());
    }

    @Test
    void getAllCertificatesBySortingNameDesc() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(GiftCertificate.builder().build());
        certificates.add(GiftCertificate.builder().build());
        Mockito.when(certificateDao.getCertificateBySortingNameDesc()).thenReturn(certificates);
        ApiResponse allCertificatesBySortingNameDesc = certificateService.getAllCertificatesBySortingNameDesc();
        assertEquals(certificates,allCertificatesBySortingNameDesc.getObject());
    }

    @Test
    void getAllCertificatesBySortingTime() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(GiftCertificate.builder().build());
        certificates.add(GiftCertificate.builder().build());
        Mockito.when(certificateDao.getCertificateBySortingTime()).thenReturn(certificates);
        ApiResponse allCertificatesBySortingTime = certificateService.getAllCertificatesBySortingTime();
        assertEquals(certificates,allCertificatesBySortingTime.getObject());
    }

    @Test
    void getAllCertificatesBySortingTimeDesc() {
        List<GiftCertificate> certificates = new ArrayList<>();
        certificates.add(GiftCertificate.builder().build());
        certificates.add(GiftCertificate.builder().build());
        Mockito.when(certificateDao.getCertificateBySortingTimeDesc()).thenReturn(certificates);
        ApiResponse allCertificatesBySortingTimeDesc = certificateService.getAllCertificatesBySortingTimeDesc();
        assertEquals(certificates,allCertificatesBySortingTimeDesc.getObject());
    }


}