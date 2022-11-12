package ia.deliz.app.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@Relation(value = "error", collectionRelation = "errors")
public class ErrorModel extends RepresentationModel<ErrorModel> {

  private final ErrorType type;
  private final String message;
}
