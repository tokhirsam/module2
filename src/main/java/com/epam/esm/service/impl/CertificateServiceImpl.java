package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.enums.HttpStatus;
import com.epam.esm.model.GiftCertificate;
import com.epam.esm.model.Tag;
import com.epam.esm.payload.ApiResponse;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {


    private final CertificateDao certificateDao;
    private final TagDao tagDao;




    @Override
    public ApiResponse getCertificateById(Long id) {

        try {
            GiftCertificate certificate = certificateDao.getCertificateById(id);
            List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(id);
            certificate.setTags(tagsByCertificateId);
            return new ApiResponse("Certificate fetched successfully ID = " + id, HttpStatus.OK_200, certificate);
        } catch (Exception e) {
            return new ApiResponse("Certificate ID not found. ID = " + id, HttpStatus.NO_CONTENT_204);
        }

    }

    @Override
    public List<GiftCertificate> getAllCertificates() {
        List<GiftCertificate> allCertificates = certificateDao.getAllCertificates();
        for (GiftCertificate certificate : allCertificates) {
            List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(certificate.getId());
            certificate.setTags(tagsByCertificateId);
        }
        return allCertificates;
    }

    @Override
    public ApiResponse addCertificate(GiftCertificateDto dto) {
        Integer certificateId = certificateDao.addCertificateReturningId(dto);
        String[] tags = dto.getTags();
        for (String tag : tags) {
            Integer tagIdByName = certificateDao.findTagIdByName(tag);
            if (tagIdByName>0){
                certificateDao.insertToCertificate_Tags(certificateId, tagIdByName);
            }else {
                Integer addTagReturningId = tagDao.addTagReturningId(tag);
               certificateDao.insertToCertificate_Tags(certificateId, addTagReturningId);
            }
        }
        return new ApiResponse("New Certificate added successfully", HttpStatus.CREATED_201);
    }

    @Override
    public ApiResponse deleteCertificate(Long id) {
        if (certificateDao.deleteCertificateTagsById(id) && certificateDao.deleteCertificateById(id)) {
            return new ApiResponse("Certificate deleted successfully", HttpStatus.OK_200);
        } else {
            return new ApiResponse("Certificate ID not found: " + id, HttpStatus.NO_CONTENT_204);
        }

    }

    @Override
    public ApiResponse updateCertificate(GiftCertificateDto dto, Long id){
        if (certificateDao.updateCertificate(dto, id) && certificateDao.deleteCertificateTagsById(id)) {
            for (String tag : dto.getTags()) {
                Integer tagIdByName = certificateDao.findTagIdByName(tag);
                certificateDao.insertToCertificate_Tags(Math.toIntExact(id),tagIdByName);
            }
            return new ApiResponse("Certificate updated successfully", HttpStatus.OK_200);
        } else {
            return new ApiResponse("Certificate ID not found: " + id, HttpStatus.NO_CONTENT_204);
        }
    }

    @Override
    public ApiResponse getAllCertificatesByTagName(String name) {
        try{
            List<GiftCertificate> allCertificates = certificateDao.getCertificateByTagName(name);
            for (GiftCertificate certificate : allCertificates) {
                List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(certificate.getId());
                certificate.setTags(tagsByCertificateId);
            }
            return new ApiResponse("Certificates with "+name+" tag fetched",
                    HttpStatus.OK_200, allCertificates);
        }catch (Exception e){
            return new ApiResponse("Certificates with "+name+" tag does not exist",
                    HttpStatus.NO_CONTENT_204);
        }
    }

    @Override
    public ApiResponse getAllCertificatesBySorting() {
        List<GiftCertificate> allCertificates = certificateDao.getCertificateBySorting();
        for (GiftCertificate certificate : allCertificates) {
            List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(certificate.getId());
            certificate.setTags(tagsByCertificateId);
        }
        return new ApiResponse("Certificates are sorted by name ascending order", HttpStatus.OK_200,allCertificates);
    }
    @Override
    public ApiResponse getAllCertificatesBySortingNameDesc() {
        List<GiftCertificate> allCertificates = certificateDao.getCertificateBySortingNameDesc();
        for (GiftCertificate certificate : allCertificates) {
            List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(certificate.getId());
            certificate.setTags(tagsByCertificateId);
        }
        return new ApiResponse("Certificates are sorted by name descending order", HttpStatus.OK_200,allCertificates);
    }

    @Override
    public ApiResponse getAllCertificatesBySortingTime() {
        List<GiftCertificate> allCertificates = certificateDao.getCertificateBySortingTime();
        for (GiftCertificate certificate : allCertificates) {
            List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(certificate.getId());
            certificate.setTags(tagsByCertificateId);
        }
        return new ApiResponse("Certificates are sorted by time ascending order", HttpStatus.OK_200,allCertificates);
    }

    @Override
    public ApiResponse getAllCertificatesBySortingTimeDesc() {
        List<GiftCertificate> allCertificates = certificateDao.getCertificateBySortingTimeDesc();
        for (GiftCertificate certificate : allCertificates) {
            List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(certificate.getId());
            certificate.setTags(tagsByCertificateId);
        }
        return new ApiResponse("Certificates are sorted by time descending order", HttpStatus.OK_200,allCertificates);
    }

    @Override
    public ApiResponse getAllCertificatesByLikeName(String param) {
        List<GiftCertificate> allCertificates = certificateDao.getCertificateByLike(param);
        for (GiftCertificate certificate : allCertificates) {
            List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(certificate.getId());
            certificate.setTags(tagsByCertificateId);
        }
        return new ApiResponse("Certificates are found by searching by part of name: "+param, HttpStatus.OK_200,allCertificates);
    }

    @Override
    public ApiResponse getAllCertificatesByLikeDescription(String param) {
        List<GiftCertificate> allCertificates = certificateDao.getCertificateByLikeDescription(param);
        for (GiftCertificate certificate : allCertificates) {
            List<Tag> tagsByCertificateId = tagDao.getTagsByCertificateId(certificate.getId());
            certificate.setTags(tagsByCertificateId);
        }
        return new ApiResponse("Certificates are found by searching by part of description: "+param, HttpStatus.OK_200,allCertificates);
    }



}
