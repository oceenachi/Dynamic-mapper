package mapper.dto.request;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class QueryFields {

        private String[] name;

        private String[] age;

        private String[] timestamp;

}
