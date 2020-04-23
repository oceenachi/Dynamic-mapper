package mapper.service;


import mapper.dto.request.Fields;
import mapper.dto.request.Provider;
import mapper.model.ProviderFields;
import mapper.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        for(Fields field: provider.getFields()){
            ProviderFields providerFields = new ProviderFields();
            providerFields.setProviderId(provider.getProviderId());
            providerFields.setName(field.getName());
            providerFields.setAge(field.getAge());
            providerFields.setTimestamp(field.getTimestamp());
            providerRepository.save(providerFields);
        }

    }
}
