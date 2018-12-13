package ru.webservice.application.validation;

public class CoordinateValidation {
    private String message = "Coordinates are empty.";
    private double latitude;
    private double longitude;

    public void setCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {

        if (Math.abs(latitude) >= 180 || Math.abs(longitude) >= 180) {
            message = "Your coordinate values are invalid! " +
                    "Your absolute values of coordinates must be less than 180.";
            return false;
        }
        message = "valid";
        return true;
    }
}
