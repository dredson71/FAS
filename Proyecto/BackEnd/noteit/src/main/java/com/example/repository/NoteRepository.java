package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Note;
import com.example.model.Notebook;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
	
	     
	List<Note> findAllByNotebook(Notebook notebook);
	

}
