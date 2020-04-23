package mapper.controller;

import mapper.dto.request.Provider;
import mapper.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/")
public class ProviderController {

    ProviderService providerService;

    @Autowired
    public ProviderController( ProviderService providerService){
        this.providerService = providerService;
    }

    @PostMapping("/provider")
    public ResponseEntity<?> loadData(@RequestBody Provider provider){
       return providerService.addData(provider);
    }
}
