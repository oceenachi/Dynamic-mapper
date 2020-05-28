package mapper.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ResponseStructure {

    private List<Object> result= new ArrayList<>();
}
