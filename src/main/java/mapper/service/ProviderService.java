package mapper.service;
//
//import mapper.dto.request.Provider;
//import mapper.dto.request.QueryFields;
//import mapper.dto.response.ResponseData;
//import mapper.model.ProviderFields;
//import mapper.repository.ProviderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.json.simple.parser.ParseException;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
public class ProviderService {
//
//    ProviderRepository providerRepository;
//
//    @Autowired
//    public ProviderService(ProviderRepository providerRepository){
//
//        this.providerRepository = providerRepository;
//    }
//
//    //Method to load data in the database
//    public ResponseEntity<?> addData(Provider provider) throws ParseException, IOException {
//        this.setFields(provider);
//        return new ResponseEntity<Provider>(provider, HttpStatus.ACCEPTED);
//    }
//
//    //This method sets the fields of the provider class
//    private void setFields(Provider provider) throws ParseException, IOException {
//
//        for(Object field: provider.getData()){
//
//            ProviderFields providerFields = new ProviderFields();
//            providerFields.setProviderId(provider.getProviderId());
//
//            providerFields.setFields(this.formatField((Map<String, Object>) field));
//
//            providerRepository.save(providerFields);
//        }
//
//    }
//
//    private Map<String, String> formatField(Map<String, Object> input){
//
//        Map<String, String> result = new HashMap<>();
//
//        input.forEach((key,val)->{
//            if(val instanceof Integer){
//                result.put(key, ((Integer)val).toString());
//            }
//            else if(val instanceof String){
//                result.put(key, (String) val);
//            }
//        });
//        return result;
//
//    }
//
//
//
//
//
////    ○ eqc: equalsIgnoreCase (string)
////    ○ eq: equalsTo (timestamp and integer)
////    ○ lt: lessThan timestamp and integer)
////    ○ gt: greaterThan )any field timestamp and integer)
//
//    public void filterData(Long providerId, QueryFields queries){
//
//        Specification<ProviderFields> specification = Specification.where(withProviderId(providerId));
//
//        if(queries.getName() != null){
//            specification = specification
//                    .and(withName(queries.getName()[1]));
//            assert specification != null;
//            specification = specification
//                    .or(withSurName(queries.getName()[1]));
//        }
//
//        if(queries.getAge() != null){
//            assert specification != null;
//            specification = specification
//                    .and(withAge(Integer.parseInt(queries.getAge()[1]), queries.getAge()[0]));
//        }
//
//        if(queries.getTimestamp() != null){
//            assert specification != null;
//            specification = specification
//                    .and(withTimeStamp(Long.parseLong(queries.getTimestamp()[1]), queries.getTimestamp()[0]));
//        }
//
//
//        return this.mapSpecificationToResponse(providerRepository.findAll(specification));
//    }
//
//    public QueryFields mapRequestParam(Map<String, String> allParams){
//
//        QueryFields queryFields = new QueryFields();
//        System.out.println(allParams);
//
//        allParams.forEach(queryFields::set);
//
//        System.out.println(queryFields);
//
//        return queryFields;
//    }
//
//
//    private List<ResponseData> mapSpecificationToResponse(List<ProviderFields> approvedFields){
//        List<ResponseData> response = new ArrayList<>(approvedFields.size());
//        for(ProviderFields providerFields : approvedFields){
//            ResponseData responseData = new ResponseData();
////            responseData.setAge(providerFields.getAge());
////            responseData.setTimestamp(providerFields.getTimestamp());
////            responseData.setName(providerFields.getFirstName() + " " + providerFields.getSurName());
//            response.add(responseData);
//        }
//        return response;
//    }
//
//
//    private static Specification<ProviderFields> withProviderId(Long providerId){
//        return((root, criteriaQuery, criteriaBuilder) -> {
//            return criteriaBuilder
//                    .and(criteriaBuilder.equal(root.get("providerId"), providerId));
//
//        });
//    }
//
//    private static Specification<ProviderFields> withName(String name){
//        return ((root, criteriaQuery, criteriaBuilder) -> {
//            return criteriaBuilder
//                    .and(criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), name.toUpperCase()));
//
//        });
//    }
//
//    private static Specification<ProviderFields> withSurName(String name){
//        return ((root, criteriaQuery, criteriaBuilder) -> {
//            return criteriaBuilder
//                    .and(criteriaBuilder.like(criteriaBuilder.upper(root.get("surName")), name.toUpperCase()));
//
//        });
//
//    }
//
//
//    private static Specification<ProviderFields> withOtherName(String name){
//        return ((root, criteriaQuery, criteriaBuilder) -> {
//            return criteriaBuilder
//                    .and(criteriaBuilder.like(criteriaBuilder.upper(root.get("otherName")), name.toUpperCase()));
//
//        });
//
//    }
//
//    private static Specification<ProviderFields> withAge(int age, String filterCode) {
//        switch (filterCode) {
//            case "eq":
//                return ((root, criteriaQuery, criteriaBuilder) -> {
//                    return criteriaBuilder
//                            .and(criteriaBuilder.equal(root.get("age"), age));
//                });
//
//            case "gt":
//                return ((root, criteriaQuery, criteriaBuilder) -> {
//                    return criteriaBuilder
//                            .and(criteriaBuilder.greaterThan(root.get("age"), age));
//                });
//            case "lt":
//                return ((root, criteriaQuery, criteriaBuilder) -> {
//                    return criteriaBuilder
//                            .and(criteriaBuilder.lessThan(root.get("age"), age));
//                });
//        }
//        return null;
//    }
//
//
//    private static Specification<ProviderFields> withTimeStamp(Long timeStamp, String filterCode) {
//        switch (filterCode) {
//            case "eq":
//                return ((root, criteriaQuery, criteriaBuilder) -> {
//                    return criteriaBuilder
//                            .and(criteriaBuilder.equal(root.get("timestamp"), timeStamp));
//                });
//
//            case "gt":
//                return ((root, criteriaQuery, criteriaBuilder) -> {
//                    return criteriaBuilder
//                            .and(criteriaBuilder.greaterThan(root.get("timestamp"), timeStamp));
//                });
//            case "lt":
//                return ((root, criteriaQuery, criteriaBuilder) -> {
//                    return criteriaBuilder
//                            .and(criteriaBuilder.lessThan(root.get("timestamp"), timeStamp));
//                });
//        }
//        return null;
//
//    }
//
//
//
//private static Specification<ProviderFields> switchSpecifications(Map<String, String> allParams){
//
//    allParams.forEach((key, val)->{
//        String[] filters = val.split(":");
//
//        if(filters[0].equals("eqc")){
//            return ((root, criteriaQuery, criteriaBuilder) -> {
//                return criteriaBuilder
//                        .and(criteriaBuilder.lessThan(root.get("timestamp"), timeStamp));
//            });
//
//
//
//        }
//
//        }
//
//    });
//
}
