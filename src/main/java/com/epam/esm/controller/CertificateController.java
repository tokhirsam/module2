package com.epam.esm.controller;

import com.epam.esm.enums.HttpStatus;
import com.epam.esm.payload.ApiResponse;
import com.epam.esm.dto.GiftCertificateDto;
import com.epam.esm.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @RequestMapping("/")
    public String display() {
        return "index";
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getOneById(@PathVariable Long id) {
        ApiResponse tagById = certificateService.getCertificateById(id);
        return ResponseEntity.status(tagById.getCodes().equals(HttpStatus.OK_200) ?
                200 : 409).body(tagById);
    }

    @GetMapping
    public HttpEntity<?> getAll() {

        return ResponseEntity.ok(certificateService.getAllCertificates());
    }

    @PostMapping
    public HttpEntity<?> addCertificate(@RequestBody GiftCertificateDto dto) {
        return ResponseEntity.ok(certificateService.addCertificate(dto));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@RequestBody GiftCertificateDto dto, @PathVariable Long id) {
        ApiResponse apiResponse = certificateService.updateCertificate(dto, id);
        return ResponseEntity.status(apiResponse.getCodes().equals(HttpStatus.OK_200) ?
                200 : 404).body(apiResponse);

    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = certificateService.deleteCertificate(id);
        return ResponseEntity.status(apiResponse.getCodes().equals(HttpStatus.OK_200) ?
                200 : 404).body(apiResponse);
    }

    @GetMapping("/findWithTagName")
    public HttpEntity<?> getCertificatesByTagName(@RequestParam("name") String name) {
        ApiResponse tagById = certificateService.getAllCertificatesByTagName(name);
        return ResponseEntity.status(tagById.getCodes().equals(HttpStatus.OK_200) ?
                200 : 409).body(tagById);
    }

    @GetMapping("/sortByName")
    public HttpEntity<?> getCertificatesBySorting() {
        return ResponseEntity.ok(certificateService.getAllCertificatesBySorting());
    }

    @GetMapping("/sortByName/desc")
    public HttpEntity<?> getCertificatesBySortingNameDesc() {
        return ResponseEntity.ok(certificateService.getAllCertificatesBySortingNameDesc());
    }

    @GetMapping("/sortByTime")
    public HttpEntity<?> getCertificatesBySortingTime() {
        return ResponseEntity.ok(certificateService.getAllCertificatesBySortingTime());
    }

    @GetMapping("/sortByTime/desc")
    public HttpEntity<?> getCertificatesBySortingTimeDesc() {
        return ResponseEntity.ok(certificateService.getAllCertificatesBySortingTimeDesc());
    }

    @GetMapping("/findWithLikeName")
    public HttpEntity<?> getCertificatesByLikeName(@RequestParam("like") String like) {
        return ResponseEntity.ok(certificateService.getAllCertificatesByLikeName(like));
    }

    @GetMapping("/findWithLikeDescription")
    public HttpEntity<?> getCertificatesByLikeDescription(@RequestParam("like") String like) {
        return ResponseEntity.ok(certificateService.getAllCertificatesByLikeDescription(like));
    }
}
