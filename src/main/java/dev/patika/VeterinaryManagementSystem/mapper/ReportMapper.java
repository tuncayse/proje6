package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.ReportRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.ReportResponse;
import dev.patika.VeterinaryManagementSystem.entities.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    Report asEntity(ReportRequest reportRequest);

    @Named("defaultMapping")
    ReportResponse asOutput(Report report); // Regular mapping

    List<ReportResponse> asOutput(List<Report> reports); // Will use the "defaultMapping"

    void update(@MappingTarget Report entity, ReportRequest request);

    @Named("detailedMapping")
    ReportResponse reportToReportResponse(Report report); // Detailed mapping
}
