package sho;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import pl.kuligowy.ApplicationConfiguration;
import pl.kuligowy.models.Receipt;
import pl.kuligowy.models.ReceiptRepository;
import pl.kuligowy.models.Shop;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class ShopControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private Receipt receipt;
    private Shop shop;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.shop = new Shop("Biedronka");
        this.receipt = new Receipt(shop,new Date());

    }

    @Test
    public void createReceipt() throws Exception {
        String receiptJson = json(this.receipt);
        this.mockMvc.perform(post("/receipt").contentType(contentType).content(receiptJson))
                .andExpect(status().isCreated());
//                        .andExpect(header().string("location","http://localhost/receipt/[0-9]+"));
    }

    @Test
    public void receiptNotFound() throws Exception {
        mockMvc.perform(get("/receipt/2/"))
                .andExpect(status().isNotFound());
    }

//    @Test
//    public void readSingleShop() throws Exception {
//        mockMvc.perform(get("/shop/" + this.shop.getId()))
//                .andExpect(status().isOk()).andExpect(content().contentType(contentType))
//                .andExpect(jsonPath("$.id", is(this.shop.getId())))
//                .andExpect(jsonPath("$.name", is(this.shop.getName())));
//    }

    @Test
    public void readShops() throws Exception {
        mockMvc.perform(get("/shop"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    //
    // @Test
    // public void createShop() throws Exception {
    // String shop = json(new Shop(2,"Tesco",20);
    // this.mockMvc.perform(post("/shop").contentType(contentType).content(shop))
    // .andExpect(status().isCreated());
    // }
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
