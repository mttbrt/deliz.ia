package ia.deliz.app.service.impl;

import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.PrepStatusEntity;
import ia.deliz.app.repository.PrepStatusRepository;
import ia.deliz.app.service.PrepStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrepStatusServiceImpl implements PrepStatusService {

  @Autowired private PrepStatusRepository prepStatusRepository;

  @Override
  public Iterable<PrepStatusEntity> getPrepStatuses() {
    return prepStatusRepository.findAll();
  }

  @Override
  public PrepStatusEntity getPrepStatusById(Long id) {
    Optional<PrepStatusEntity> existingEntity = prepStatusRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No preparation status found with id %d", id));
    }

    return existingEntity.get();
  }
}
