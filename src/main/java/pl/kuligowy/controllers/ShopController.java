package pl.kuligowy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.kuligowy.models.Shop;
import pl.kuligowy.models.ShopRepository;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopRepository repository;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Shop> getAll() {
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Shop> save(@RequestBody Shop shop) {
		Shop result = repository.save(shop);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders
				.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result.getId()).toUri());
		return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Shop getShop(@PathVariable int id) {
		Shop shop = repository.findOne(id);
		if (shop == null)
			throw new ShopNotFoundException(id);
		return shop;
	}

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class ShopNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer shopId;

	public ShopNotFoundException(Integer shopId) {
		super("could not find shop " + shopId + "'.");
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}
