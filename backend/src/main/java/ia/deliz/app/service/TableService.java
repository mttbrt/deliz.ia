package ia.deliz.app.service;

import ia.deliz.app.model.domain.TableEntity;
import ia.deliz.app.model.dto.TableDTO;

public interface TableService {

  Iterable<TableEntity> getTables();

  TableEntity getTableById(Long id);

  TableEntity createTable(TableDTO dto);

  TableEntity replaceTable(Long id, TableDTO dto);

  TableEntity updateTable(Long id, TableDTO dto);

  void deleteTable(Long id);
}
