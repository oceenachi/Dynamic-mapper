package mapper.controller;

import mapper.dto.request.Provider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/")
public class ProviderController {

    @PostMapping("/provider")
    public ResponseEntity<?> loadData(@RequestBody Provider provider){
       return providerService.addDate(Provider);
    }
}