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
  public TableEntity getTableById(Long tableId) {
    Optional<TableEntity> existingEntity = tableRepository.findById(tableId);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No table found with id %d", tableId));
    }

    return existingEntity.get();
  }

  @Override
  public TableEntity createTable(TableDTO table) {
    TableEntity entity = new TableEntity(table);
    return tableRepository.save(entity);
  }

  @Override
  public TableEntity replaceTable(Long tableId, TableDTO table) {
    if (!tableRepository.existsById(tableId)) {
      throw new EntityNotFoundException(String.format("No table found with id %d", tableId));
    }

    TableEntity replacedEntity = new TableEntity(table);
    return tableRepository.save(replacedEntity);
  }

  @Override
  public TableEntity updateTable(Long tableId, TableDTO table) {
    Optional<TableEntity> existingEntity = tableRepository.findById(tableId);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No table found with id %d", tableId));
    }

    TableEntity updatedEntity = existingEntity.get().updateNonNullAttributes(table);
    return tableRepository.save(updatedEntity);
  }

  @Override
  public void deleteTable(Long tableId) {
    if (!tableRepository.existsById(tableId)) {
      throw new EntityNotFoundException(String.format("No table found with id %d", tableId));
    }

    tableRepository.deleteById(tableId);
  }
}
