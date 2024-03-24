package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Report;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import dev.patika.VeterinaryManagementSystem.mapper.VaccineMapper;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepository;
import dev.patika.VeterinaryManagementSystem.repository.ReportRepository;
import dev.patika.VeterinaryManagementSystem.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final ReportRepository reportRepository;
    private final VaccineMapper vaccineMapper;


    @Autowired
    public VaccineService(VaccineRepository vaccineRepository,
                          VaccineMapper vaccineMapper,
                          ReportRepository reportRepository) {
        this.vaccineRepository = vaccineRepository;
        this.vaccineMapper = vaccineMapper;
        this.reportRepository = reportRepository;
    }

    public List<VaccineResponse> findAll() {
       return vaccineMapper.asOutput(vaccineRepository.findAll());

    }

    // Değerlendirme Formu #21 : hayvanların aşı kayıtlarının tarih aralığına göre listelenmesi için
    // gerekli controller ve service katmanlarının oluşturulması
    public List<VaccineResponse> findByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> vaccines = vaccineRepository.findByProtectionStartDateBetween(startDate, endDate);
        return vaccineMapper.asOutput(vaccines);
    }


    public Vaccine findById (Long id) {
        return vaccineRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + "id li Vaccine Bulunamadı !!!"));
    }


    public void deleteById(Long id) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if (vaccineFromDb.isPresent()) {
            vaccineRepository.delete(vaccineFromDb.get());
        } else {
            throw new RuntimeException(id + "id li aşı sistemde bulunamadı !!!");
        }
    }

    // Değerlendirme Formu #15 : hayvana ait aşı kaydetmek için gerekli controller ve
    // service katmanlarının oluşturulması

    // Değerlendirme Formu #19 : yeni aşı kaydedilirken koruyuculuk tarihi geçmişse
    // veya aynı isimde aşı daha önce kaydedilmemişse yeni aşı kaydedilebilir.
    // Koruyuculuk tarihi geçmemişse ve aynı isimde yeni aşı ekleniyor ise hata mesajı verilmelidir.


    public VaccineResponse update(Long id, VaccineRequest request) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        Optional<Vaccine> isVaccineExist = vaccineRepository.findByCode(request.getCode());

        if (vaccineFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız aşı sistemde bulunamadı. !!!.");
        }

        if (isVaccineExist.isPresent()) {
            throw new RuntimeException("Bu aşı daha önce sisteme kayıt olmuştur !!!");
        }

        Vaccine vaccine = vaccineFromDb.get();
        vaccineMapper.update(vaccine, request);
        return vaccineMapper.asOutput(vaccineRepository.save(vaccine));
    }

/*
    public VaccineResponse createVaccine(VaccineRequest vaccineRequest) {
        Report report = reportRepository.findById(vaccineRequest.getReportId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid report ID"));
        Vaccine vaccine = vaccineMapper.asEntity(vaccineRequest);
        vaccine.setReport(report);
        Vaccine savedVaccine = vaccineRepository.save(vaccine);
        return vaccineMapper.asOutput(savedVaccine);
    } */

    public VaccineResponse createVaccine(VaccineRequest vaccineRequest) {
        Report report = reportRepository.findById(vaccineRequest.getReportId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid report ID"));
        Vaccine vaccine = vaccineMapper.asEntity(vaccineRequest);
        vaccine.setReport(report);
        Vaccine savedVaccine = vaccineRepository.save(vaccine);
        return vaccineMapper.asOutput(savedVaccine);
    }



}