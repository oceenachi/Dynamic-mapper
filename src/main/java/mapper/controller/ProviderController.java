package mapper.controller;

import mapper.dto.request.Provider;
import mapper.model.ProviderFields;
import mapper.service.NewProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/")
public class ProviderController {

    NewProviderService newProviderService;

    @Autowired
    public ProviderController( NewProviderService newProviderService){
        this.newProviderService = newProviderService;
    }

    @PostMapping("/provider")
    public ResponseEntity<?> loadData(@RequestBody Provider provider) throws IOException {
       return newProviderService.addData(provider);
    }


    @GetMapping("/filter/{providerId}")
    private List<ProviderFields> filterData(@PathVariable("providerId") String providerId,
                                            @RequestParam Map<String, String> allParams) {

        return newProviderService.filterData(Long.parseLong(providerId), allParams);

    }

}
