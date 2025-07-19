package omar.HyperCell.intern.task2.exception;

import lombok.Getter;

@Getter
public abstract class AppException extends Exception {
    private final Data data;
    protected AppException() {
        super() ;
        data = null;
    }

    protected AppException(ErrorCode errorCode ) {
        super(errorCode.getMsg());
        this.data = Data.fromErrorCode(errorCode);
    }


    public record Data(Integer code, String msg) {
        public static Data fromErrorCode(ErrorCode errorCode) {
            return new Data(errorCode.getCode(), errorCode.getMsg());
        }
    }
}
