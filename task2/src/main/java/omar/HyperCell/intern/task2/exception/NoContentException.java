package omar.HyperCell.intern.task2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends AppException{

    public NoContentException() {
        super();
    }
    public NoContentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
