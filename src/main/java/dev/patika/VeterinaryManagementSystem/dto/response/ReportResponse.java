package dev.patika.VeterinaryManagementSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private double price;
    private Long appointmentId;
}
