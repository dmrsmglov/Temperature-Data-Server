package ru.webservice.application.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.webservice.application.domain.TemperatureMessage;

import java.util.List;

public interface TemperatureMessageRepo extends CrudRepository<TemperatureMessage, Long> {
    List<TemperatureMessage> findByCoordinatesEqualsOrderByTime(String coordinates);
    List<TemperatureMessage> findByOrderByTime();
}
