package com.example.noquake;

import com.google.firebase.firestore.Exclude;

public class Note2 {
    private String documentId;

    private String tarih;
    private String saat;
    private String yer;
    private String büyüklük;
    public Note2() {
        //public no-arg constructor needed
    }
    @Exclude
    public String getDocumentId() {return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }

    public Note2(String tarih, String saat, String yer, String buyukluk) {
        this.tarih = tarih; //ad
        this.saat = saat; //numara
        this.yer = yer;
        this.büyüklük = buyukluk;
    }

    public String getTarih() {
        return tarih;
    }
    public String getSaat() {
        return saat;
    }
    public String getYer() {
        return yer;
    }
    public String getBüyüklük() {
        return büyüklük;
    }
}
