const BASE_URL = "https://proje6backend.onrender.com/api/v1/appointmentDate";

export const AppointmentService = {
    getAllAppointments: async () => {
        const response = await fetch(`${BASE_URL}`);
        if (!response.ok) {
            throw new Error('Randevu verileri alınamadı.');
        }
        return await response.json();
    },

    getAppointmentById: async (id) => {
        const response = await fetch(`${BASE_URL}/${id}`);
        if (!response.ok) {
            throw new Error('Randevu bilgisi alınamadı.');
        }
        return await response.json();
    },

     createAppointment: async (doctorId, animalId, appointmentDate) => {
        const response = await fetch(`/api/v1/appointmentDate/create-with-doctor-and-animal/${doctorId}/${animalId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ appointmentDate })
        });
        if (!response.ok) {
            throw new Error('Failed to create appointment.');
        }
        return await response.json();
    },

    updateAppointment: async (id, appointmentDate, doctorId, animalId) => {
        const response = await fetch(`/api/v1/appointmentDate/update/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ appointmentDate, doctorId, animalId })
        });
        if (!response.ok) {
            throw new Error('Failed to update appointment.');
        }
        return await response.json();
    },

    deleteAppointment: async (id) => {
        const response = await fetch(`${BASE_URL}/delete/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error('Randevu silme başarısız.');
        }
        return response.ok;
    }
};
