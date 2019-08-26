package com.example.api.viewmodel;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NoteViewModel {

	private Long id;

	@NotNull
	@Min(3)
	private String title;

	@NotNull
	private String text;

	private Long notebookId;

	private Date lastModifiedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(Long notebookId) {
		this.notebookId = notebookId;
	}

	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}

	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

}