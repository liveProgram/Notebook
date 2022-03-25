package com.live.programming.an15_notebook.models;

public class NoteModel {
    private String noteId;
    private String noteTitle;
    private String dateTime;
    private String noteDesc;

    public NoteModel(String noteId, String noteTitle, String dateTime, String noteDesc) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.dateTime = dateTime;
        this.noteDesc = noteDesc;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getNoteDesc() {
        return noteDesc;
    }
}
