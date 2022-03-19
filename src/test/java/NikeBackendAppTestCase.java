import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NikeBackendAppTestCase.class})
public class NikeBackendAppTestCase {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new NikeBackendApp()).build();
    }

    @Test
    public void getShoePrice() throws Exception {
        RequestBuilder requestBuilder = get("/api/shoe-price/1");
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("shoePrice")));
    }
}
