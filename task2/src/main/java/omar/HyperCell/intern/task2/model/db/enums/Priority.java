package omar.HyperCell.intern.task2.model.db.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Priority {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    ;

    private static final Map<Integer,Priority> PRIORITY_MAP = new HashMap<>();

    static{
        for(Priority priority: Priority.values()){
            PRIORITY_MAP.put(priority.getId(), priority);
        }
    }

    public static Priority valueOf(int id) {
        return PRIORITY_MAP.get(id);
    }

    private final int id;
    Priority(int id) {
        this.id = id;
    }
}
