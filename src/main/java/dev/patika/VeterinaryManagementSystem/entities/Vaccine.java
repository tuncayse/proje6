package dev.patika.VeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "vaccine")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "protection_start_date", nullable = false)
    private LocalDate protectionStartDate;

    @Column(name = "protection_finish_date", nullable = false)
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;


    public Long getReportId() {
        return report != null ? report.getId() : null;
    }
}
