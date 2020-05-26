package mapper.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import mapper.dto.InputFields;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class ProviderFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    private String firstName;
//
//    private String surName;
//
//    private String otherName;
//
//    private int age;
//
//    private Long timestamp;
//    @OneToMany
//    private List<InputFields> fields = new ArrayList<>();

    private Long providerId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="keyValueFields")
    @MapKeyColumn(name="dataFields")
    private Map<String, String> fields = new HashMap<String, String>(7);

//    @JsonAnyGetter
//    public Map<String, Object> any() {
//        return fields;
//    }
//
//    @JsonAnySetter
//    public void set(String name, Object value) {
//        fields.put(name, value);
//    }



}
