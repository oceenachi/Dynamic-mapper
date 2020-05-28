package mapper.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.*;

@Data
@Document
public class ProviderFields {

    @Id
    private String id;

    private Long providerId;

    private Map<String, Object> fields;



}
