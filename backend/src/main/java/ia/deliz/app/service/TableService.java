package ia.deliz.app.service;

import ia.deliz.app.domain.entity.Table;
import ia.deliz.app.domain.model.TableModel;

public interface TableService {

  Iterable<Table> getTables();

  Table createTable(TableModel table);

  Table replaceTable(Long tableId, TableModel table);

  Table updateTable(Long tableId, TableModel table);

  void deleteTable(Long tableId);
}
