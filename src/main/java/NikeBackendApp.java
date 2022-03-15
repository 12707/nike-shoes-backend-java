import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableAutoConfiguration
public class NikeBackendApp {

    @GetMapping("/api/shoe-price/{id}")
    @ResponseBody
    public String getShoePrice(@PathVariable String id) {
        String url = "https://bi8cxjuyll.execute-api.us-west-2.amazonaws.com/prices/shoes?id="+id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(NikeBackendApp.class, args);
    }

}
