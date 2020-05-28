package mapper.service;

import mapper.dto.request.Provider;
import mapper.dto.request.QueryFields;
import mapper.model.ProviderFields;
import mapper.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class NewProviderService {

        ProviderRepository providerRepository;
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

        public List<Object> filterData(Long providerId, Map<String, String> allParams){
            this.mapRequestParam(allParams);

            System.out.println("allParams: "+ allParams);

            Query dynamicQuery = new Query();
            Criteria newCriteria = Criteria.where("providerId").is(providerId);
            dynamicQuery.addCriteria(newCriteria);

            allParams.forEach((key, val) ->{

                String[] filters  = val.split(":");
                System.out.println(Arrays.toString(filters));

                if (filters[0].equals("eqc")) {
                    Criteria eqcCriteria = Criteria.where(("fields." + key)).regex( this.toLikeRegex(filters[1].trim()), "i");
                    dynamicQuery.addCriteria(eqcCriteria);
                }

                if (filters[0].equals("eq")) {
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

        return this.mapResultToResponse(result);
        }

        private String toLikeRegex(String source) {
            return source.replaceAll("\\*", ".*");
        }

        public QueryFields mapRequestParam(Map<String, String> allParams){

            QueryFields queryFields = new QueryFields();

            allParams.forEach(queryFields::set);

            return queryFields;
        }


        private List<Object> mapResultToResponse(List<ProviderFields> approvedFields){

            List<Object> response = new ArrayList<>(approvedFields.size());
            for(ProviderFields providerFields : approvedFields){
                response.add(providerFields.getFields());
            }

            return response;
        }

    }
