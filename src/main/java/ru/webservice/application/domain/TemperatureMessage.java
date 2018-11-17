package ru.webservice.application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class TemperatureMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String temperature;
    private Integer time;
    private String coordinates;

    public TemperatureMessage() {
    }

    public TemperatureMessage(String temperature, String coordinates, Integer time) {
        this.temperature = temperature;
        this.coordinates = coordinates;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
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
