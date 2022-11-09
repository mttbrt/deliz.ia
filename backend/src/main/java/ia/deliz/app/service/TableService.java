package ia.deliz.app.service;

import ia.deliz.app.model.domain.TableEntity;
import ia.deliz.app.model.dto.TableDTO;

public interface TableService {

  Iterable<TableEntity> getTables();

  TableEntity getTableById(Long tableId);

  TableEntity createTable(TableDTO table);

  TableEntity replaceTable(Long tableId, TableDTO table);

  TableEntity updateTable(Long tableId, TableDTO table);

  void deleteTable(Long tableId);
}
