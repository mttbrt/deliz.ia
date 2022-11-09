package ia.deliz.app.service.impl;

import ia.deliz.app.repository.TableRepository;
import ia.deliz.app.service.TableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TableServiceImplTest {

  @InjectMocks private TableService service;

  @Mock private TableRepository repository;

  // TODO: test service layer

  @Test
  void getTables() {}

  @Test
  void getTableById() {}

  @Test
  void createTable() {}

  @Test
  void replaceTable() {}

  @Test
  void updateTable() {}

  @Test
  void deleteTable() {}
}
