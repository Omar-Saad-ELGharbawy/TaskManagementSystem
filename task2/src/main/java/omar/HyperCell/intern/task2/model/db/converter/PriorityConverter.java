package omar.HyperCell.intern.task2.model.db.converter;

import jakarta.persistence.AttributeConverter;
import omar.HyperCell.intern.task2.model.db.enums.Priority;

public class PriorityConverter implements AttributeConverter<Priority, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Priority attribute) {
        return attribute.getId();
    }

    @Override
    public Priority convertToEntityAttribute(Integer dbData) {
        return Priority.valueOf(dbData);
    }
}
