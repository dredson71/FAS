package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.api.viewmodel.NoteViewModel;
import com.example.api.viewmodel.NotebookViewModel;
import com.example.model.Note;
import com.example.model.Notebook;
import com.example.repository.NotebookRepository;

@Component
public class Mapper {

	@Autowired
	private NotebookRepository notebookRepository;

	public NoteViewModel convertToNoteViewModel(Note entity) {
		NoteViewModel viewModel = new NoteViewModel();
		viewModel.setTitle(entity.getTitle());
		viewModel.setId(entity.getId());
		viewModel.setLastModifiedOn(entity.getLastModifiedOn());
		viewModel.setText(entity.getText());
		viewModel.setNotebookId(entity.getNotebook().getId());

		return viewModel;
	}

	public Note convertToNoteEntity(NoteViewModel viewModel) {
		Notebook notebook = this.notebookRepository.findById(viewModel.getNotebookId()).get();
		Note entity = new Note(viewModel.getId(), viewModel.getTitle(), viewModel.getText(), notebook);

		return entity;
	}

	public NotebookViewModel convertToNotebookViewModel(Notebook entity) {
		NotebookViewModel viewModel = new NotebookViewModel();
		viewModel.setId(entity.getId());
		viewModel.setName(entity.getName());
		

		return viewModel;
	}

	public Notebook convertToNotebookEntity(NotebookViewModel viewModel) {
		Notebook entity = new Notebook(viewModel.getId(), viewModel.getName());

		return entity;
	}

}