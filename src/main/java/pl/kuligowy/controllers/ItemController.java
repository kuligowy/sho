package pl.kuligowy.controllers;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.kuligowy.models.Item;


@RestController
public class ItemController {
 
 
    @RequestMapping("/item")
    @ResponseBody
    public List<Item> getAll() {
    	ArrayList<Item> list = new ArrayList<Item>();
        return list;
    }
}
