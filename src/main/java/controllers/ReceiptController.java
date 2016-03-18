package controllers;

 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Receipt;


@RestController
public class ReceiptController {
 
 

    @RequestMapping("/receipt")
    public List<Receipt> receipt() {
    	ArrayList<Receipt> list = new ArrayList<Receipt>();
        list.add(new Receipt("biedronka",new BigDecimal(123)));
        list.add(new Receipt("lidl",new BigDecimal(1000)));
        
        return list;
    }
}
