package mapper.dto.response;

import lombok.Data;
import java.sql.Timestamp;


@Data
public class ResponseData {

    private String name;

    private int age;

    private Long timestamp;
}
