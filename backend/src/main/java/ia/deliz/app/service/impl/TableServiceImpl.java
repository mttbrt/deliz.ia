package ia.deliz.app.service.impl;

import ia.deliz.app.domain.entity.Table;
import ia.deliz.app.domain.model.TableModel;
import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.repository.TableRepository;
import ia.deliz.app.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TableServiceImpl implements TableService {

  @Autowired private TableRepository tableRepository;

  @Override
  public Iterable<Table> getTables() {
    return tableRepository.findAll();
  }

  @Override
  public Table createTable(TableModel table) {
    Table entity = new Table(table);
    return tableRepository.save(entity);
  }

  @Override
  public Table replaceTable(Long tableId, TableModel table) {
    if (!tableRepository.existsById(tableId)) {
      throw new EntityNotFoundException(String.format("No table found with id %d", tableId));
    }

    Table replacedEntity = new Table(table);
    return tableRepository.save(replacedEntity);
  }

  @Override
  public Table updateTable(Long tableId, TableModel table) {
    Optional<Table> existingEntity = tableRepository.findById(tableId);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No table found with id %d", tableId));
    }

    Table updatedEntity = existingEntity.get().updateNonNullAttributes(table);
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
