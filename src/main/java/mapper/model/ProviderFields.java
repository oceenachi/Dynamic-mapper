package mapper.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProviderFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String surName;

    private String otherName;

    private int age;

    private Long timestamp;

    private Long providerId;

}
