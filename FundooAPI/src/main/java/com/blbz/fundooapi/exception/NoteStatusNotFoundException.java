package com.blbz.fundooapi.exception;

public class NoteStatusNotFoundException extends Exception {
    public NoteStatusNotFoundException() {
        super("Wrong status passed");
    }

    public NoteStatusNotFoundException(String s) {
        super(s);
    }
}
