package ca.sheridancollege.rajparad.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.rajparad.bean.Item;

import ca.sheridancollege.rajparad.database.DatabaseAccess;

@Controller
public class HomeController {

	@Autowired
	private DatabaseAccess da;

	@GetMapping("/")
	public String getIndex() {

		return "index.html";
	}

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied.html";
	}

	@GetMapping("/allItems")
	public String getIndex(Model model, Item i) {
		ArrayList<Item> list = da.getAll();
		model.addAttribute("items", list);
		
		return "allItems";
	}

	@GetMapping("/addItem")
	public String getaddItem(Model model, Item i) {



		return "addItem";
	}

	@PostMapping("/addItem")
	public String insertAppointment(Model model, @ModelAttribute Item item) {
		da.insertItem(item);

		return "addItem";
	}
	
	
	@GetMapping("/deleteItems")
	public String getDeleteItem(Model model, Item i) {



		return "deleteItems";
	}
	

	@GetMapping("/edit/{id}")
	public String editLink(Model model, @PathVariable int id){
		Item a = da.getItemById(id);
		model.addAttribute("item", a);
		return "editItem";
	}
	
	
	
	

	@GetMapping("/modify")
	public String modifyAppointment(Model model, @ModelAttribute Item item) {
		da.modifyItem(item);
		ArrayList<Item> list = da.getAll();
		model.addAttribute("items", list);

		long i = item.getId();
		return "redirect:/showItem/"+i;
		}
	
	@GetMapping("/delete/{id}")
	public String deleteLink(Model model, @PathVariable int id,  @ModelAttribute Item item){
		da.deleteItemById(id);
		ArrayList<Item> list =  da.getAll();
		model.addAttribute("items",list);
		return "showIds";
	}
	
	@GetMapping("/showIds")
	public String getshowids(Model model, Item items) {
		ArrayList<Item> list = da.getAll();
		model.addAttribute("items", list);
		return "showIds";
	}
	@PostMapping("/showIds")
	public String getitem(Model model, @ModelAttribute Item item) {
		//da.insertItem(item);
		long i = item.getId();
		return "redirect:/showItem/"+i;
	}
	
	@GetMapping("/showItem/{id}")
	public String getIndex(Model model,  @PathVariable int id, Item item) {
		Item a = da.getItemById(id);
		model.addAttribute("item", a);
		return "showItem";
		}
	
	@GetMapping("/delete/")
	public String deleteAllLink(Model model,  @ModelAttribute Item item){
		da.deleteAllItem();;
		ArrayList<Item> list =  da.getAll();
		model.addAttribute("items",list);
		return "redirect:/showIds";
	}
	
	@GetMapping("/delete/quantity")
	public String deletequaLink(Model model,  @ModelAttribute Item item){
		da.deleteQuaItem(0);
		ArrayList<Item> list =  da.getAll();
		model.addAttribute("items",list);
		return "redirect:/showIds";
	}
}
