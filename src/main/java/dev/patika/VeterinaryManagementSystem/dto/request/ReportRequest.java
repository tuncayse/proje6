package dev.patika.VeterinaryManagementSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {
    private String title;
    private String diagnosis;
    private double price;
    private Long appointmentId;

}
