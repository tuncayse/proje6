package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.ReportRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.ReportResponse;
import dev.patika.VeterinaryManagementSystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/create")
    public ResponseEntity<ReportResponse> createReport(@RequestBody ReportRequest reportRequest) {
        ReportResponse reportResponse = reportService.createReport(reportRequest);
        return new ResponseEntity<>(reportResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> getReportById(@PathVariable Long id) {
        ReportResponse reportResponse = reportService.getById(id);
        return ResponseEntity.ok(reportResponse);
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> getAllReports() {
        List<ReportResponse> reports = reportService.findAll();
        return ResponseEntity.ok(reports);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReportResponse> updateReport(@PathVariable Long id, @RequestBody ReportRequest reportRequest) {
        ReportResponse reportResponse = reportService.update(id, reportRequest);
        return ResponseEntity.ok(reportResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
