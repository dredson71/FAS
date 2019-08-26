package com.example.api;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Notebook;
import com.example.repository.NotebookRepository;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {

	@Autowired
	private NotebookRepository notebookRepository;

	
	@GetMapping
	public List<Notebook> all() {
		List<Notebook> noteBooks = this.notebookRepository.findAll();
		return noteBooks;
	}

	@PostMapping
	public Notebook save(@RequestBody Notebook noteBook, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException();
		}

		
		Notebook noteBookNew=this.notebookRepository.save(noteBook);

		return noteBookNew;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.notebookRepository.deleteById(id);
	}

}