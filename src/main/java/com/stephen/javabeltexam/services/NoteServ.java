package com.stephen.javabeltexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.stephen.javabeltexam.models.Note;
import com.stephen.javabeltexam.repositories.NoteRepo;

@Service
public class NoteServ {
	@Autowired NoteRepo noteRepo;
	
	public List<Note>allNote(){
		return noteRepo.findAll();
	}
	
	public Note createNote(Note note) {
		return noteRepo.save(note);
	}
	
	public Note findNoteId(Long noteId) {
		Optional<Note> optNote = noteRepo.findById(noteId);
		if(optNote.isPresent()) {
			return optNote.get();
		}else {
			return null;
		}
	}
	
	public List<Note>listingNotes(Long listingId){
		return noteRepo.findByListingId(listingId);
	}
	
	public void deleteNote(Note note) {
		noteRepo.delete(note);
	}
	
}
