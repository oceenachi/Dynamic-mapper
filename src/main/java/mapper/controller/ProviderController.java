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
    private List<ResponseData> filterData(@PathVariable("providerId") String providerId,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String age,
                                            @RequestParam(required = false) String timestamp) {

       return providerService.filterData(Long.parseLong(providerId), providerService.mapRequestParam(name, age, timestamp));

    }

}
