package com.learn.storage.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "storeData")
public class StoreData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Nonnull
	private String title;
	@Nonnull
	private String text;
        
	public StoreData(){}
	
	public StoreData(String title, String text) {
		super();
		this.title = title;
		this.text = text;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
