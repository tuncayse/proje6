package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Report;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface VaccineMapper {


    /*default Vaccine asEntity(VaccineRequest vaccineRequest) {
        if (vaccineRequest == null) {
            return null;
        }

        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineRequest.getName());
        vaccine.setCode(vaccineRequest.getCode());
        vaccine.setProtectionStartDate(vaccineRequest.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineRequest.getProtectionFinishDate());

        return vaccine;
    } */

    Vaccine asEntity(VaccineRequest vaccineRequest);


    VaccineResponse asOutput(Vaccine vaccine);

    List<VaccineResponse> asOutput(List<Vaccine> vaccines);

    void update(@MappingTarget Vaccine entity, VaccineRequest request);
}
