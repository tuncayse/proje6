// Report.java
package dev.patika.VeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String diagnosis;

    private double price;

    // Report ile One-to-One ilişkisi
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_date_id")
    private AppointmentDate appointmentDate;


    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vaccine> vaccines;


    public Long getAppointmentId() {
        if (this.appointmentDate != null) {
            return this.appointmentDate.getId();
        } else {
            return null;
        }
    }








}