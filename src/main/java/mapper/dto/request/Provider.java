package mapper.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class Provider {

    private Long providerId;

    private List<Object> data;
}
