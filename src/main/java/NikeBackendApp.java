import com.fasterxml.jackson.core.JsonGenerator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@EnableAutoConfiguration
public class NikeBackendApp {
    // It is a flag that determine to whether using discount
    private boolean isDiscounted = true;

    // The rate of discount
    private float discountRate = 0.4f;

    @GetMapping("/api/shoe-price/{id}")
    @ResponseBody
    public String getShoePrice(@PathVariable String id) {
        String url = "https://bi8cxjuyll.execute-api.us-west-2.amazonaws.com/prices/shoes?id="+id;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return generateNewPrice(result);
    }

    /**
     * Based on the parameters (isDiscounted, discountRate) to covert the origin json to new format.
     * @param json
     * @return
     */
    private String generateNewPrice(String json) {
        String result = json;
        if (isDiscounted) {
            JsonElement jsonElement = new JsonParser().parse(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            float originalPrice = jsonObject.getAsJsonPrimitive("shoePrice").getAsFloat();
            float discountedPrice = originalPrice * discountRate;
            JsonElement discountedPriceElement = new JsonParser().parse(String.valueOf(new BigDecimal(discountedPrice).setScale(2, RoundingMode.HALF_UP).floatValue()));
            JsonElement originalPriceElement = new JsonParser().parse(String.valueOf(originalPrice));
            jsonObject.add("originalPrice", originalPriceElement);
            jsonObject.add("shoePrice", discountedPriceElement);
            result = jsonObject.toString();
        }
        return result;
    }

    public static void main(String[] args) {
//        String msg = "{\"shoePrice\":142}";
//        String ret = new NikeBackendApp().generateNewPrice(msg);
//        System.out.println(ret);
        SpringApplication.run(NikeBackendApp.class, args);
    }

}
