package ia.deliz.app.service;

import ia.deliz.app.model.domain.PrepStatusEntity;

public interface PrepStatusService {

  Iterable<PrepStatusEntity> getPrepStatuses();

  PrepStatusEntity getPrepStatusById(Long id);
}
