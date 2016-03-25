package pl.kuligowy.controllers;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.kuligowy.models.Receipt;
import pl.kuligowy.models.ReceiptRepository;
 


@RestController
public class ReceiptController {
 
	@Autowired
	private ReceiptRepository receiptRepository;
 

    @ResponseBody
    @RequestMapping("/receipt")
    public List<Receipt> receipt() {
    	return this.receiptRepository.findAll();
    }
}
