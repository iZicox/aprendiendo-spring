package com.spring.relaciones_1.controller;

import com.spring.relaciones_1.dto.ApplicantDto;
import com.spring.relaciones_1.service.ApplicantService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService service) {
        this.applicantService = service;
    }


    // create
    @PostMapping
    public ResponseEntity<ApplicantDto> save(@Valid @RequestBody ApplicantDto applicantDto) {
        ApplicantDto newApplicant = applicantService.createAplicant(applicantDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newApplicant);
    }

    // list all
    @GetMapping
    public ResponseEntity<Page<ApplicantDto>> getAll(Pageable pageable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicantService.findAll(pageable));
    }

    // list by id
    @GetMapping("{id}")
    public ResponseEntity<ApplicantDto> getOne(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicantService.findById(id));
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        applicantService.deleteAplicantById(id);
        return ResponseEntity
                .noContent().build();
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<ApplicantDto> update(@PathVariable Long id, @Valid @RequestBody ApplicantDto applicantDto) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicantService.updateAplicant(applicantDto, id));
    }
}
