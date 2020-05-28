package mapper.dto.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


//@Data
public class QueryFields {

        private Map<String, String> other = new HashMap<>();

        @JsonAnyGetter
        public Map<String, String> any() {
            return other;
        }

        @JsonAnySetter
        public void set(String name, String value) {
            other.put(name, value);
        }

}
