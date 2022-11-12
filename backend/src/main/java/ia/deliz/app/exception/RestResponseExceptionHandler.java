package ia.deliz.app.exception;

import ia.deliz.app.controller.TableController;
import ia.deliz.app.model.domain.TableEntity;
import ia.deliz.app.model.response.ErrorModel;
import ia.deliz.app.model.response.ErrorType;
import ia.deliz.app.model.response.TableAssembler;
import ia.deliz.app.model.response.TableModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@ControllerAdvice
public class RestResponseExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public CollectionModel<ErrorModel> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    List<ErrorModel> errors = new LinkedList<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            e -> {
              ErrorModel error = new ErrorModel(ErrorType.VALIDATION_ERROR, e.getDefaultMessage());
              errors.add(error);
            });
    CollectionModel<ErrorModel> errorCollection = CollectionModel.of(errors);
    return errorCollection.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  protected ErrorModel handleEntityNotFoundException(EntityNotFoundException ex) {
    ErrorModel error = new ErrorModel(ErrorType.ENTITY_NOT_FOUND, ex.getMessage());
    return error.add(linkTo(methodOn(TableController.class).getTables()).withRel("TODO")); // TODO: link to error definition
  }
}
