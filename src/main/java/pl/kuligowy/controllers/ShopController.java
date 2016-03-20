package pl.kuligowy.controllers;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.kuligowy.models.Shop;


@RestController
public class ShopController {
 
 
    @RequestMapping("/shop")
    @ResponseBody
    public List<Shop> getAll() {
    	ArrayList<Shop> list = new ArrayList<Shop>();
        return list;
    }
}
