package mapper.model;

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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="keyValueFields")
    @MapKeyColumn(name="dataFields")
    private Map<String, String> fields = new HashMap<>(7);


    private Long providerId;

}
