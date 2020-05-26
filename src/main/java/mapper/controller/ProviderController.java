package mapper.controller;

import mapper.dto.request.Provider;
import mapper.dto.request.QueryFields;
import mapper.dto.response.ResponseData;
import mapper.model.ProviderFields;
import mapper.service.ProviderService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
public class ProviderController {

    ProviderService providerService;

    @Autowired
    public ProviderController( ProviderService providerService){
        this.providerService = providerService;
    }

    @PostMapping("/provider")
    public ResponseEntity<?> loadData(@RequestBody Provider provider) throws ParseException, IOException {
       return providerService.addData(provider);
    }


    @GetMapping("/filter/{providerId}")
    private void filterData(@PathVariable("providerId") String providerId,
                                            @RequestParam Map<String, String> allParams) {

        providerService.filterData(Long.parseLong(providerId), providerService.mapRequestParam(allParams));

    }

}
