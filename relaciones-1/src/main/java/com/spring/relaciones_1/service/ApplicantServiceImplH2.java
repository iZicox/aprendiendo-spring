package com.spring.relaciones_1.service;

import com.spring.relaciones_1.dto.ApplicantDto;
import com.spring.relaciones_1.dto.ResumeDto;
import com.spring.relaciones_1.exception.ApplicantNotFound;
import com.spring.relaciones_1.model.Applicant;
import com.spring.relaciones_1.model.Resume;
import com.spring.relaciones_1.repository.ApplicantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ApplicantServiceImplH2 implements ApplicantService {

    private final ApplicantRepository repository;
    // inyeccion por constructor
    public ApplicantServiceImplH2(ApplicantRepository repository) {
        this.repository = repository;
    }

    // crear applicant
    @Override
    @Transactional
    public ApplicantDto createAplicant(ApplicantDto applicantDto) {
        Applicant applicant = applicantDto.getEntity(); // generar applicant con el dto
        // revisar si el dto tiene resumen o si es nulo
        if(applicantDto.resumeDto() != null){
            // en el caso que no sea nulo generamos la entidad resume

            Resume resume = new Resume(
                    null,                            // lo hacemos con id nulo
                    applicantDto.resumeDto().content(), // agregamos el content del dto
                    applicant);                         // le asignamos el applicant creado anteriormente
            applicant.setResume(resume); // le asignamos el resumen creado a la entidad applicant
            // debido al cascade al hacer el save de applicant creara tambien el resumen en la base de datos
        }
        // guardamos los datos en la base de datos
        Applicant saved = repository.save(applicant);
        // ahora tendremos el id del applicant y resumen en el caso que no sea nulo

        // devolvemos la informacion en un dto
        return new ApplicantDto(
                saved.getId(),
                saved.getName(),
                saved.getPhone(),
                saved.getEmail(),
                saved.getStatus(),
                applicantDto.resumeDto() != null ? ResumeDto.getDto(saved.getResume()) : null
        );
    }


    /**
     * listar todos usando pageable
     * @param pageable
     * @return
     */
    @Override
    public Page<ApplicantDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(ApplicantDto::getDto);

    }

    /**
     * listar un applicant por id
     * @param id
     * @return
     */
    @Override
    public ApplicantDto findById(Long id) {
        return ApplicantDto.getDto(repository.findById(id).orElseThrow(() -> new ApplicantNotFound("Applicant not found with id " + id)));
    }

    /**
     * borrar usando id
     * @param id
     */
    @Override
    @Transactional
    public void deleteAplicantById(Long id) {
        if(!repository.existsById(id)){
            throw new ApplicantNotFound("Applicant not found with id " + id);
        }
        repository.deleteById(id);
    }

    /**
     * actualizar completo
     * @param applicantDto
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ApplicantDto updateAplicant(ApplicantDto applicantDto, Long id) {
        // buscamos cual es la entidad a actualizar
        Applicant old = repository.findById(id).orElseThrow(() -> new ApplicantNotFound("Applicant not found with id " + applicantDto.id()));
        // extraemos el resume
        Resume oldResume = old.getResume();

        // hacemos los cambios por medio de setters
        old.setName(applicantDto.name());
        old.setPhone(applicantDto.phone());
        old.setEmail(applicantDto.email());
        old.setStatus(applicantDto.status());
        oldResume.setContent(applicantDto.resumeDto().content());

        // como esta en la misma transaccion y la entidad esta siendo manejada no hace falta usar el save

        return ApplicantDto.getDto(old);
    }
}
