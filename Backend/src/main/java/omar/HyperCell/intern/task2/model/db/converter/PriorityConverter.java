package omar.HyperCell.intern.task2.model.db.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import omar.HyperCell.intern.task2.model.db.enums.Priority;

@Converter
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
