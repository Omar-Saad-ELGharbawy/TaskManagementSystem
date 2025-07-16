package omar.HyperCell.intern.task2.model.db.converter;

import jakarta.persistence.AttributeConverter;
import lombok.NoArgsConstructor;
import omar.HyperCell.intern.task2.model.db.enums.MyEnumsInterface;
import omar.HyperCell.intern.task2.model.db.enums.Priority;
import omar.HyperCell.intern.task2.model.db.enums.Status;


//make generic converter for all enums
public class GenericConverter<E extends Enum<E> & MyEnumsInterface> implements AttributeConverter<E, Integer> {

    private final Class<E> enumType;



    public GenericConverter(Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Integer convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.getId();
    }

    @Override
    public E convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        if (enumType.equals(Priority.class) ) {
            return enumType.cast(Priority.valueOf(dbData));
        } else if (enumType.equals( Status.class)) {
            return enumType.cast(Status.valueOf(dbData));
        }
        throw new IllegalArgumentException("Unsupported enum type: " + enumType.getName());
    }

}
