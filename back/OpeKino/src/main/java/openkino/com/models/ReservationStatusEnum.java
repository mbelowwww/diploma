package openkino.com.models;

public enum ReservationStatusEnum {
    booked("Забронировано"),
    notBooked("Свободно");

    private String status;

    ReservationStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

