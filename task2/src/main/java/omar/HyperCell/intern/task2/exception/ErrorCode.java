package omar.HyperCell.intern.task2.exception;

import lombok.Getter;
@Getter
public enum ErrorCode {
    INVALID_USER_ID(1000,"ID is either empty or null"),
    OUT_OF_RANGE_USER_ID(1001,"User ID is out of range"),
    INVALID_USER_NAME(400,"User name is either empty or null"),
    INVALID_USER_EMAIL(400,"Email is either empty or null"),

    INVALID_PROJECT_TITLE(400,"Project title is either empty or null"),
    INVALID_PROJECT_DESCRIPTION(400,"Project description is either empty or null"),
    INVALID_PROJECT_ID(400,"Project ID is either empty or null"),
    OUT_OF_RANGE_PROJECT_ID(400,"Project ID is out of range"),


    EMPTY_TASK_LIST(204,"Task list is empty"),
    INVALID_TASK_ID(400,"Task ID is either empty or null"),
    OUT_OF_RANGE_TASK_ID(400,"Task ID is out of range"),
    INVALID_TASK_TITLE(400,"Task title is either empty or null"),
    INVALID_TASK_DESCRIPTION(400,"Task description is either empty or null"),
    INVALID_TASK_STATUS(400,"Task status is either empty or null"),
    INVALID_TASK_PRIORITY(400,"Task priority is either empty or null"),
    INVALID_TASK_DUE_DATE(400,"Task due date is either empty or null"),
    INVALID_TASK_ASSIGNED_USER(400,"Task assigned user is either empty or null"),
    INVALID_TASK_PROJECT(400,"Task project is either empty or null"),

    ;
    private final int code;
    private final String msg;
//    private long timeStamp;

    ErrorCode(int code) {
        this.code = code;
        this.msg = name();
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
//    void setTimeStamp(long timeStamp) {
//        this.timeStamp = timeStamp;
//    }
}
