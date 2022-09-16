package com.stephen.javabeltexam.controllers;




import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stephen.javabeltexam.models.Listing;
import com.stephen.javabeltexam.models.Note;
import com.stephen.javabeltexam.models.User;
import com.stephen.javabeltexam.services.ListingServ;
import com.stephen.javabeltexam.services.NoteServ;
import com.stephen.javabeltexam.services.UserServ;

@Controller
public class ListingController {
	@Autowired ListingServ listingServ;
	@Autowired UserServ userServ;
	@Autowired NoteServ noteServ;
	
	@GetMapping("/home")
	public String dashboard(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", userServ.findById(userId));
			model.addAttribute("listings", listingServ.allListings());
			return "dashboard.jsp";
		}
	}
	
	@GetMapping("/listings/new")
	public String newListing(@ModelAttribute("listing")Listing listing, HttpSession session,Model model) {
		User loggedUser = userServ.findById((Long) session.getAttribute("userId"));
		if(loggedUser == null) {
			return "redirect:/logout";
		}else {
			model.addAttribute("user", loggedUser);
			return "newListing.jsp";
		}
	}
	
	@PostMapping("listings/create")
	public String createListing(@Valid @ModelAttribute("listing")Listing listing, BindingResult result,
			HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		if(result.hasErrors()) {
			return "newListing.jsp";
		}else {
			listingServ.createListing(listing);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/listings/{id}")
	public String showListing(@PathVariable("id") Long id, HttpSession session, Model model,
			@ModelAttribute("note") Note note) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		} else {
			User loggedUser = userServ.findById(userId);
			Listing showListing = listingServ.findListingId(id);
			model.addAttribute("user", loggedUser);
			model.addAttribute("listing", showListing);

			return "showListing.jsp";
		}
	}
	
	@PostMapping("/listings/{id}")
	public String addNote( @Valid @ModelAttribute("note")Note note, BindingResult result, @PathVariable("id") Long id, HttpSession session,
			 Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		}
		Listing listing = listingServ.findListingId(id);
		User loggedUser = userServ.findById(userId);

		if(result.hasErrors()) {
			model.addAttribute("user", loggedUser);
			model.addAttribute("listing", listing);
			return "showListing.jsp";
		}else {
			Note newNote = new Note(note.getText());
			newNote.setListing(listing);
			newNote.setUser(loggedUser);
			noteServ.createNote(newNote);
			return "redirect:/listings/{id}";
		}
	}
	
	
	@GetMapping("/listings/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/logout";
		}else {
			User loggedUser = userServ.findById(userId);
			Listing showListing = listingServ.findListingId(id);
			model.addAttribute("user", loggedUser);
			model.addAttribute("listing", showListing);
			return "editListing.jsp";
		}
	}
	
	@PutMapping("/listings/{id}/update")
	public String updateBook(@Valid @ModelAttribute("listing") Listing listing, BindingResult result,
			HttpSession session,@PathVariable("id") Long id ) {
		Long userId = (Long) session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/logout";
		}
		
		if (result.hasErrors()) {
    		return "editListing.jsp";
    	}else {
    		User loggedUser = userServ.findById(userId);
    		listing.setUser(loggedUser);
    		session.setAttribute("userId", loggedUser.getId());
    		listingServ.updateListing(listing);
    		return "redirect:/home";
    	}
	}
	
	@RequestMapping("/listings/{id}/delete")
	public String destroy(@PathVariable("id")Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/logout";
		}
		
		for(Note text:noteServ.listingNotes(id)) {
			noteServ.deleteNote(text);
		}
			
		listingServ.deleteListing(id);
		return "redirect:/home";
	}
	
	

}
