package com.stephen.javabeltexam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.javabeltexam.models.Note;

@Repository
public interface NoteRepo extends CrudRepository<Note, Long>{
	public List<Note>findAll();
	public List<Note>findByListingId(Long id);
}
