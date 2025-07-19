package omar.HyperCell.intern.task2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends AppException {

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
