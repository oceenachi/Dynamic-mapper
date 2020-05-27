package mapper.service;

import mapper.dto.request.Provider;
import mapper.dto.request.QueryFields;
import mapper.dto.response.ResponseData;
import mapper.model.ProviderFields;
import mapper.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class NewProviderService {

        ProviderRepository providerRepository;

//        @Inject
        private MongoTemplate mongoTemplate;

        @Autowired
        public NewProviderService(ProviderRepository providerRepository, MongoTemplate mongoTemplate){

            this.providerRepository = providerRepository;
            this.mongoTemplate = mongoTemplate;
        }

        //Method to load data in the database
        public ResponseEntity<?> addData(Provider provider) {
            this.setFields(provider);
            return new ResponseEntity<Provider>(provider, HttpStatus.ACCEPTED);
        }

        //This method sets the fields of the provider class
        private void setFields(Provider provider) {

            for(Object field: provider.getData()){

                ProviderFields providerFields = new ProviderFields();
                providerFields.setProviderId(provider.getProviderId());

//                providerFields.setFields(this.formatField((Map<String, Object>) field));
                providerFields.setFields((Map<String, Object>) field);

                providerRepository.save(providerFields);
            }

        }

        private Map<String, String> formatField(Map<String, Object> input){

            Map<String, String> result = new HashMap<>();

            input.forEach((key,val)->{
                if(val instanceof Integer){
                    result.put(key, ((Integer)val).toString());
                }
                else if(val instanceof String){
                    result.put(key, (String) val);
                }
            });
            return result;

        }



//    ○ eqc: equalsIgnoreCase (string)
//    ○ eq: equalsTo (timestamp and integer)
//    ○ lt: lessThan timestamp and integer)
//    ○ gt: greaterThan )any field timestamp and integer)

        public List<ProviderFields> filterData(Long providerId, Map<String, String> allParams){
            this.mapRequestParam(allParams);

            System.out.println("allParams: "+ allParams);

            Query dynamicQuery = new Query();
            Criteria newCriteria = Criteria.where("providerId").is(providerId);
            dynamicQuery.addCriteria(newCriteria);

            allParams.forEach((key, val) ->{

                String[] filters  = val.split(":");
                System.out.println(filters[0]);
                System.out.println(filters[1] + 500);

                if (filters[0].equals("eqc")) {
                    Criteria eqcCriteria = Criteria.where(("fields." + key).toLowerCase()).regex((String) filters[1].toLowerCase());
                    dynamicQuery.addCriteria(eqcCriteria);
                }

                if (filters[0].equals("eq")) {
//                    System.out.println("HERE: " +Criteria.where("fields." + key));
                    Criteria eqCriteria = Criteria.where("fields." + key).is(Long.parseLong(filters[1]));
                    dynamicQuery.addCriteria(eqCriteria);
                }

                if (filters[0].equals("lt")) {
                    Criteria ltCriteria = Criteria.where("fields." + key).lt(Long.parseLong(filters[1]));
                    dynamicQuery.addCriteria(ltCriteria);
                }

                if (filters[0].equals("gt")) {
                    Criteria gtCriteria = Criteria.where("fields." + key).gt(Long.parseLong(filters[1]));
                    dynamicQuery.addCriteria(gtCriteria);
                }
            });

            List<ProviderFields> result =
                    mongoTemplate.find(dynamicQuery, ProviderFields.class, "providerFields");

            System.out.println(dynamicQuery);

            System.out.println(result);
        return result;
        }

        public QueryFields mapRequestParam(Map<String, String> allParams){

            QueryFields queryFields = new QueryFields();

            allParams.forEach(queryFields::set);

            System.out.println(queryFields);

            return queryFields;
        }


        private List<ResponseData> mapResultToResponse(List<ProviderFields> approvedFields){
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


    }
