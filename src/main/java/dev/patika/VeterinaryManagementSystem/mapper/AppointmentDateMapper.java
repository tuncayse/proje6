package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.AppointmentDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AppointmentDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper
public interface AppointmentDateMapper {



    AppointmentDate asEntity(AppointmentDateRequest appointmentDateRequest);

    default AppointmentDateResponse asOutput(AppointmentDate appointmentDate) {
        if (appointmentDate == null) {
            return null;
        }

        AppointmentDateResponse response = new AppointmentDateResponse();
        response.setId(appointmentDate.getId());
        response.setAppointmentDate(appointmentDate.getAppointmentDate());
        response.setDoctorId(appointmentDate.getDoctor() != null ? appointmentDate.getDoctor().getId() : null);
        response.setAnimalId(appointmentDate.getAnimal() != null ? appointmentDate.getAnimal().getId() : null);

        return response;
    }


    List<AppointmentDateResponse> asOutput(List<AppointmentDate> appointmentDate);

    void update(@MappingTarget AppointmentDate entity, AppointmentDateRequest appointmentDateRequest);

}
