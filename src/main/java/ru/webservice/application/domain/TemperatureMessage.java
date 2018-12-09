package ru.webservice.application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Calendar;

@Entity
public class TemperatureMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String temperature;
    private Long time = Calendar.getInstance().getTimeInMillis();
    private String coordinates;

    public TemperatureMessage() {
    }

    public TemperatureMessage(String temperature, String coordinates) {
        this.temperature = temperature;
        this.coordinates = coordinates;
    }

    public Integer getId() {
        return id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;

    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
