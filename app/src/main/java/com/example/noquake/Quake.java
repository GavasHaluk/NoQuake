package com.example.noquake;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class Quake extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_TITLE = "Tarih";
    private static final String KEY_DESCRIPTION = "Yer";
    private static final String KEY_Country = "Büyüklük";
    private static final String KEY_Saat = "Saat";

    public TextView Bk;
    public TextView St;
    private TextView textViewData; //textViewData

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference depremRef = db.collection("Depremler");
    private DocumentReference noteRef = db.document("Depremler/1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quake);
        // editTextTitle
        TextView tar = findViewById(R.id.Tar);
        //editTextDescription
        TextView yr = findViewById(R.id.Yr);
        Bk = findViewById(R.id.Bk);
        St = findViewById(R.id.St);
        textViewData = findViewById(R.id.text_view_data);

    }
    @Override
    protected void onStart() {
        super.onStart();
        depremRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    return;
                }
                StringBuilder data = new StringBuilder();

                assert queryDocumentSnapshots != null;
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    Note2 note = documentSnapshot.toObject(Note2.class);
                    note.setDocumentId(documentSnapshot.getId());

                    String documentId = note.getDocumentId();
                    String tarihh = note.getTarih();
                    String saatt = note.getSaat();
                    String yerr = note.getYer();
                    String buyuklukk = note.getBüyüklük();


                    data.append("\nBüyüklük: ").append(buyuklukk).append("\nYer: ").append(yerr).append("\nTarih: ").append(tarihh).append(" "+"/"+" "+"Saat:").append(saatt).append("\n\n");

                }
                textViewData.setText(data.toString());

            }

        });

    }

}
