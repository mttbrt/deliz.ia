package ia.deliz.app.service.impl;

import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.TableEntity;
import ia.deliz.app.model.dto.TableDTO;
import ia.deliz.app.repository.TableRepository;
import ia.deliz.app.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

  @Autowired private TableRepository tableRepository;

  @Override
  public Iterable<TableEntity> getTables() {
    return tableRepository.findAll();
  }

  @Override
  public TableEntity getTableById(Long id) {
    Optional<TableEntity> existingEntity = tableRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No table found with id %d", id));
    }

    return existingEntity.get();
  }

  @Override
  public TableEntity createTable(TableDTO dto) {
    TableEntity entity = new TableEntity(dto);
    return tableRepository.save(entity);
  }

  @Override
  public TableEntity replaceTable(Long id, TableDTO dto) {
    if (!tableRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("No table found with id %d", id));
    }

    TableEntity replacedEntity = new TableEntity(dto);
    return tableRepository.save(replacedEntity);
  }

  @Override
  public TableEntity updateTable(Long id, TableDTO dto) {
    Optional<TableEntity> existingEntity = tableRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No table found with id %d", id));
    }

    TableEntity updatedEntity = existingEntity.get().updateNonNullAttributes(dto);
    return tableRepository.save(updatedEntity);
  }

  @Override
  public void deleteTable(Long id) {
    if (!tableRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("No table found with id %d", id));
    }

    tableRepository.deleteById(id);
  }
}
