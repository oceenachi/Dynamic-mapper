package mapper.service;


import mapper.dto.request.Fields;
import mapper.dto.request.Provider;
import mapper.dto.request.QueryFields;
import mapper.dto.response.ResponseData;
import mapper.model.ProviderFields;
import mapper.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProviderService {

    ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository){
        this.providerRepository = providerRepository;

    }

    public ResponseEntity<?> addData(Provider provider){
        this.setFields(provider);
        return new ResponseEntity<Provider>(provider, HttpStatus.ACCEPTED);
    }

    private void setFields(Provider provider){

        for(Fields field: provider.getData()){
            ProviderFields providerFields = new ProviderFields();
            providerFields.setProviderId(provider.getProviderId());
            providerFields.setName(field.getName());
            providerFields.setAge(field.getAge());
            providerFields.setTimestamp(field.getTimestamp());
            providerRepository.save(providerFields);
        }

    }
//    ○ eqc: equalsIgnoreCase (string)
//    ○ eq: equalsTo (timestamp and integer)
//    ○ lt: lessThan timestamp and integer)
//    ○ gt: greaterThan )any field timestamp and integer)

    public List<ProviderFields> filterData(Long providerId, QueryFields queries){
        Specification<ProviderFields> specification = Specification.where(withProviderId(providerId));

        if(queries.getName() != null){
            specification = specification
                    .and(withName(queries.getName()[1]));
        }

        if(queries.getAge() != null){
            assert specification != null;
            specification = specification
                    .and(withAge(Integer.parseInt(queries.getAge()[1]), queries.getAge()[0]));
        }

        if(queries.getTimestamp() != null){
            assert specification != null;
            specification = specification
                    .and(withTimeStamp(Timestamp.valueOf(queries.getTimestamp()[1]), queries.getTimestamp()[0]));
        }

        System.out.println(providerRepository.findAll(specification));

        return providerRepository.findAll(specification);
    }

    public QueryFields mapRequestParam(String name, String age, String timestamp){

        QueryFields queryFields = new QueryFields();
        if(name != null){
            queryFields.setName(name.split(":"));
        }
        if(age != null ){
            queryFields.setAge(age.split(":"));
        }
        if(timestamp != null){
            queryFields.setTimestamp(timestamp.split(":"));
        }
        return queryFields;
    }


    private static Specification<ProviderFields> withProviderId(Long providerId){
        return((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder
                    .and(criteriaBuilder.equal(root.get("providerId"), providerId));
        });

    }

    private static Specification<ProviderFields> withName(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            System.out.println("name " + criteriaBuilder.upper(root.get("name")));
            System.out.println("name2 " + name.toUpperCase() );
            return criteriaBuilder
                    .and(criteriaBuilder.equal(criteriaBuilder.upper(root.get("name")), name.toUpperCase()));

        });

    }

    private static Specification<ProviderFields> withAge(int age){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder
                    .and(criteriaBuilder.equal(root.get("age"), age));
        });
    }


    private static Specification<ProviderFields> withAge(int age, String filterCode) {
        switch (filterCode) {
            case "eq":
                return ((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder
                            .and(criteriaBuilder.equal(root.get("age"), age));
                });

            case "gt":
                return ((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder
                            .and(criteriaBuilder.greaterThan(root.get("age"), age));
                });
            case "lt":
                return ((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder
                            .and(criteriaBuilder.lessThan(root.get("age"), age));
                });
        }
        return null;
    }


    private static Specification<ProviderFields> withTimeStamp(Timestamp timeStamp, String filterCode) {
        switch (filterCode) {
            case "eq":
                return ((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder
                            .and(criteriaBuilder.equal(root.get("timestamp"), timeStamp));
                });

            case "gt":
                return ((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder
                            .and(criteriaBuilder.greaterThan(root.get("timestamp"), timeStamp));
                });
            case "lt":
                return ((root, criteriaQuery, criteriaBuilder) -> {
                    return criteriaBuilder
                            .and(criteriaBuilder.lessThan(root.get("timestamp"), timeStamp));
                });
        }
        return null;

    }

}
