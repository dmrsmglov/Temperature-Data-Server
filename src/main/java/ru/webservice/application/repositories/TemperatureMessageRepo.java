package ru.webservice.application.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.webservice.application.domain.TemperatureMessage;

import java.util.List;

public interface TemperatureMessageRepo extends CrudRepository<TemperatureMessage, Long> {
    List<TemperatureMessage> findFirst10ByLatitudeEqualsAndLongitudeEqualsOrderByTimeDesc(double latitude, double longitude);
    List<TemperatureMessage> findFirst10ByOrderByTimeDesc();
}
