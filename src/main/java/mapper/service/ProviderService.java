package mapper.service;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import mapper.dto.InputFields;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProviderService {

    ProviderRepository providerRepository;

    @Autowired
    public ProviderService(ProviderRepository providerRepository){
        this.providerRepository = providerRepository;

    }

    //Method to load data in the database
    public ResponseEntity<?> addData(Provider provider) throws ParseException, IOException {
        this.setFields(provider);
        return new ResponseEntity<Provider>(provider, HttpStatus.ACCEPTED);
    }

    //This method sets the fields of the provider class
    private void setFields(Provider provider) throws ParseException, IOException {

        for(Object field: provider.getData()){

            ProviderFields providerFields = new ProviderFields();
            providerFields.setProviderId(provider.getProviderId());
            providerFields.setFields((Map<String, String>) field);


////            Gson gson = new Gson;
//            JSONParser parser = new JSONParser();
//            System.out.println("got here");

//            System.out.println(field.toString());
//            String jsonField = new Gson().toJson(field);
//            System.out.println(jsonField);
//            JSONObject obj = (JSONObject) parser.parse(field.toString());
//            JSONObject obj =
//            System.out.println(field.toString());
//            Map<String, String> dynamicField = (Map<String, String>) obj.entrySet();

//            System.out.println(dynamicField);

//            for(Map.Entry entries: field){
//            String stringField = field.toString();
//            System.out.println( stringField);
//                HashMap result =
//                        new ObjectMapper().readValue(stringField, HashMap.class);
//                System.out.println(result);

//            }
//            HashMap result =
//                    new ObjectMapper().readValue((byte[]) field, HashMap.class);
//            System.out.println(result);
//            Map<String, String> dynamicFields= new HashMap<>();


//            Map<String, Object> input= new HashMap<String, Object>();


//            providerFields.setFields(result);












            InputFields inputField = new InputFields();
//            inputField.setInputFields(input);







//            providerFields.setFields();
//            String[] fullName = field.getName().split("\\s+");
//
//
//            assert fullName[0] != null;
//            providerFields.setFirstName(fullName[0]);
//            assert fullName[1] != null;
//            providerFields.setSurName(fullName[1]);
//            assert fullName[2] != null;
//            if(fullName.length > 2){
//                providerFields.setOtherName(fullName[2]);
//            }
//            providerFields.setAge(field.getAge());
//            providerFields.setTimestamp(field.getTimestamp());
            providerRepository.save(providerFields);
        }

    }
//    ○ eqc: equalsIgnoreCase (string)
//    ○ eq: equalsTo (timestamp and integer)
//    ○ lt: lessThan timestamp and integer)
//    ○ gt: greaterThan )any field timestamp and integer)

    public List<ResponseData> filterData(Long providerId, QueryFields queries){

        Specification<ProviderFields> specification = Specification.where(withProviderId(providerId));

        if(queries.getName() != null){
//            System.out.println(Arrays.toString(queries.getName()));
            specification = specification
                    .and(withName(queries.getName()[1]));
            assert specification != null;
            specification = specification
                    .or(withSurName(queries.getName()[1]));
        }

        if(queries.getAge() != null){
            assert specification != null;
            specification = specification
                    .and(withAge(Integer.parseInt(queries.getAge()[1]), queries.getAge()[0]));
        }

        if(queries.getTimestamp() != null){
            assert specification != null;
            specification = specification
                    .and(withTimeStamp(Long.parseLong(queries.getTimestamp()[1]), queries.getTimestamp()[0]));
        }

//        System.out.println(providerRepository.findAll(specification));

        return this.mapSpecificationToResponse(providerRepository.findAll(specification));
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


    private List<ResponseData> mapSpecificationToResponse(List<ProviderFields> approvedFields){
        List<ResponseData> response = new ArrayList<>(approvedFields.size());
        for(ProviderFields providerFields : approvedFields){
            ResponseData responseData = new ResponseData();
//            responseData.setAge(providerFields.getAge());
//            responseData.setTimestamp(providerFields.getTimestamp());
//            responseData.setName(providerFields.getFirstName() + " " + providerFields.getSurName());
            response.add(responseData);
        }
        return response;
    }


    private static Specification<ProviderFields> withProviderId(Long providerId){
        return((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder
                    .and(criteriaBuilder.equal(root.get("providerId"), providerId));

        });
    }

    private static Specification<ProviderFields> withName(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder
                    .and(criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), name.toUpperCase()));

        });

    }

    private static Specification<ProviderFields> withSurName(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder
                    .and(criteriaBuilder.like(criteriaBuilder.upper(root.get("surName")), name.toUpperCase()));

        });

    }


    private static Specification<ProviderFields> withOtherName(String name){
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder
                    .and(criteriaBuilder.like(criteriaBuilder.upper(root.get("otherName")), name.toUpperCase()));

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


    private static Specification<ProviderFields> withTimeStamp(Long timeStamp, String filterCode) {
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
