package mapper.dto;

import lombok.Data;


import java.util.Map;

@Data
public class InputFields {

    private Long Id;

    private Map<String,String> inputFields;


}
