package omar.HyperCell.intern.task2.model.db.enums;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Status {
    TODO(1),
    IN_PROGRESS(2),
    DONE(3),
    ;

    private static final Map<Integer,Status> STATUS_MAP = new HashMap<>();
    static {
        for(Status status: Status.values()){
            STATUS_MAP.put(status.getId(), status);
        }
    }

    public static Status valueOf(int id) {
        return STATUS_MAP.get(id);
    }

    private final int id;

    Status(int id){
        this.id = id;
    }


}

