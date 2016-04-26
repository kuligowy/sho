package pl.kuligowy.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pl.kuligowy.models.Article;
import pl.kuligowy.models.ArticleRepository;

@RestController
@RequestMapping("/item")
public class ItemController {

	private ArticleRepository repository;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Article> getAll() {
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Article> saveItem(@RequestBody Article item) {
		Article newItem = repository.save(item);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders
				.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(newItem.getId()).toUri());
		ResponseEntity<Article> response = new ResponseEntity<Article>(newItem, httpHeaders, HttpStatus.CREATED);
		return response;
	}

}
