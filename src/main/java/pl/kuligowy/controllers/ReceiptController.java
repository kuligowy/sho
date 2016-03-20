package pl.kuligowy.controllers;

 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.kuligowy.models.Receipt;


@RestController
public class ReceiptController {
 
 
    @RequestMapping("/receipt")
    @ResponseBody
    public List<Receipt> receipt() {
    	ArrayList<Receipt> list = new ArrayList<Receipt>();
        return list;
    }
}
