package mapper.dto.request;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Fields {

    private String name;

    private int age;

    private Timestamp timestamp;
}
