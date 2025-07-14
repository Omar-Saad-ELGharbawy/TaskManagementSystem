package omar.HyperCell.intern.task2.model.db.converter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import omar.HyperCell.intern.task2.model.db.enums.Status;

import javax.print.attribute.Attribute;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Status attribute) {
        return attribute.getId();
    }

    @Override
    public Status convertToEntityAttribute(Integer dbData) {
        return Status.valueOf(dbData);
    }
}
