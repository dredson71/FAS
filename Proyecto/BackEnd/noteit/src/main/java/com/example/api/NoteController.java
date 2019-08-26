package com.example.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
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

import com.example.Mapper;
import com.example.api.viewmodel.NoteViewModel;
import com.example.model.Note;
import com.example.model.Notebook;
import com.example.repository.NoteRepository;
import com.example.repository.NotebookRepository;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private NotebookRepository notebookRepository;
	@Autowired
	private Mapper mapper;

	@GetMapping
	public List<NoteViewModel> all() {
		List<Note> notes = this.noteRepository.findAll();

		// map from entity to view model
		List<NoteViewModel> notesViewModel = notes.stream().map(note -> this.mapper.convertToNoteViewModel(note))
				.collect(Collectors.toList());

		return notesViewModel;
	}

	@GetMapping("/{id}")
	public NoteViewModel byId(@PathVariable Long id) {
		Note note = this.noteRepository.findById(id).orElse(null);

		if (note == null) {
			throw new EntityNotFoundException();
		}

		NoteViewModel noteViewModel = this.mapper.convertToNoteViewModel(note);

		return noteViewModel;
	}

	@GetMapping("/byNotebook/{notebookId}")
	public List<NoteViewModel> byNotebook(@PathVariable Long notebookId) {
		List<Note> notes = new ArrayList<>();

		Optional<Notebook> notebook = this.notebookRepository.findById(notebookId);
		if (notebook.isPresent()) {
			notes = this.noteRepository.findAllByNotebook(notebook.get());
		}

		// map to note view model
		List<NoteViewModel> notesViewModel = notes.stream().map(note -> this.mapper.convertToNoteViewModel(note))
				.collect(Collectors.toList());

		return notesViewModel;
	}

	@PostMapping
	public Note save(@RequestBody NoteViewModel noteCreateViewModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException();
		}

		Note noteEntity = this.mapper.convertToNoteEntity(noteCreateViewModel);

		// save note instance to db
		this.noteRepository.save(noteEntity);

		return noteEntity;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.noteRepository.deleteById(id);
	}

}