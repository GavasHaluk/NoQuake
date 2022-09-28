package com.example.noquake;

import com.google.firebase.firestore.Exclude;

public class Note {
    private String documentId;
    private String ad;
    private String numara;
    public Note() {
    }
    @Exclude
    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Note(String ad, String numara) {
        this.ad = ad;
        this.numara = numara;
    }

    public String getAd() {
        return ad;
    }
    public String getNumara() {
        return numara;
    }


}
