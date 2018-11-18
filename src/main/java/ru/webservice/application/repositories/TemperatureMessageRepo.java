package ru.webservice.application.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.webservice.application.domain.TemperatureMessage;

public interface TemperatureMessageRepo extends CrudRepository<TemperatureMessage, Long> {
    Iterable<TemperatureMessage> findByCoordinatesEqualsOrderByTimeDesc(String coordinates);
    Iterable<TemperatureMessage> findByOrderByTimeDesc();
}
