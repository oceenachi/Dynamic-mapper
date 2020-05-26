package mapper.dto;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;


@Entity
@Data
public class InputFields {

    @Id
    private Long Id;

    @ElementCollection
    private Map<String,String> inputFields;


}
