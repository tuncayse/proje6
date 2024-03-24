package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository <Vaccine,Long> {



    Optional<Vaccine> findById (Long id);

    Optional<Vaccine> findByCode(String code);


    List<Vaccine> findByReport(Report report);


    List<Vaccine> findByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Vaccine> findByReportAndName(Report report, String name);

}
