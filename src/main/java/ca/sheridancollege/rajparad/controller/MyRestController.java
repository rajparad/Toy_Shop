package ca.sheridancollege.rajparad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.rajparad.bean.Item;
import ca.sheridancollege.rajparad.database.DatabaseAccess;

@RestController
@RequestMapping("/items")
public class MyRestController {
	@Autowired
	DatabaseAccess da;

	@GetMapping("/")
	public List<Item> getStudentCollection() {
		if (da.findAll().size() == 0) {
				da.save(new Item(1,"bus","blue",99.00, 24));
		}
		return da.findAll();
	}

	@GetMapping("/{id}")
	public Item getIndividualStudent(@PathVariable Long id) {
		return da.findById(id).get(0);
	}

	@PostMapping(consumes = "application/json")
	public Long postStudent(@RequestBody Item i) {
		return da.save(i);
	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable int id) {
		da.deleteItemById(id);;
		return "Item removed.";
	}
}
