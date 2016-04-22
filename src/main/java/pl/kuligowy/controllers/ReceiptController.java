package pl.kuligowy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.kuligowy.models.Receipt;
import pl.kuligowy.models.ReceiptRepository;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

	@Autowired
	private ReceiptRepository receiptRepository;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Receipt> receipt() {
		return this.receiptRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Receipt> save(@RequestBody Receipt receipt) {
		Receipt newReceipt = receiptRepository.save(receipt);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newReceipt.getId()).toUri());
		ResponseEntity<Receipt> response = new ResponseEntity<Receipt>(newReceipt, headers, HttpStatus.CREATED);
		return response;
	}
}
