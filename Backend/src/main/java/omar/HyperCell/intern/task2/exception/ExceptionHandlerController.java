package omar.HyperCell.intern.task2.exception;

import jakarta.persistence.Convert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<AppException.Data> handleAppException(ValidationException e) {

        return new ResponseEntity<>(e.getData(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<AppException.Data> handleAppException() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
