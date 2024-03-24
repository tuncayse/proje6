package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AvailableDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper
public interface AvailableDateMapper {

    AvailableDate asEntity(AvailableDateRequest availableDateRequest);


    List<AvailableDateResponse> asOutput(List<AvailableDate> availableDate);

    void update(@MappingTarget AvailableDate entity, AvailableDateRequest request);
    default AvailableDateResponse asOutput(AvailableDate availableDate) {
        if (availableDate == null) {
            return null;
        }
        AvailableDateResponse response = new AvailableDateResponse();
        response.setId(availableDate.getId());
        response.setAvailableDate(availableDate.getAvailableDate());
        response.setDoctorId(availableDate.getDoctor() != null ? availableDate.getDoctor().getId() : null);
        return response;
    }
}
