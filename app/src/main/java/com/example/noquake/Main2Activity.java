package com.example.noquake;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_TITLE = "Name";
    private static final String KEY_DESCRIPTION = "Phone Number";
    private static final String KEY_Country = "Country Code";

    public EditText Name; // editTextTitle
    public EditText Phone_Num; //editTextDescription
    public TextView CountryCode;
    public TextView textViewData; //textViewData
    Button send;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference numberRef = db.collection("Contacts");
    //private DocumentReference noteRef = db.collection("Contacts").document(note.getDocumentId());

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Name = findViewById(R.id.Name);
        Phone_Num = findViewById(R.id.Phone_Num);
        CountryCode = findViewById(R.id.CountryCode);
        textViewData = findViewById(R.id.text_view_data);
        send = findViewById(R.id.sendtext);

        send.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            send.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }

    /*public void deleteNode(Note note) {
        //FirebaseFirestore db = FirebaseFirestore.getInstance();
        //DocumentReference noteRef = db.collection("Contacts").document(note.getDocumentId());
        db.collection("Contacts").document("123itJgQQL6xJ7w4RnDz")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }*/


    @Override
    protected void onStart() {
        super.onStart();
        numberRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                StringBuilder data = new StringBuilder();
                assert queryDocumentSnapshots != null;
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Note note = documentSnapshot.toObject(Note.class);
                    note.setDocumentId(documentSnapshot.getId());

                    String documentId = note.getDocumentId();
                    String isim = note.getAd();
                    String numara = note.getNumara();


                    data.append("Name: ").append(isim).append("\nPhone Number: ").append(numara).append("\n\n");
                }
                textViewData.setText(data.toString());
            }
        });

    }


    int i = 0;

    public void addNote(View v) {
        i += 1;
        if (i < 6) {
            String isim = Name.getText().toString();
            String Pho_num = Phone_Num.getText().toString();
            String country_code_text = CountryCode.getText().toString();
            //String description = Phone_Num.getText().toString();
            String complete_phone_number = "+" + country_code_text + Pho_num;
            Note note = new Note(isim, complete_phone_number);
            numberRef.add(note);
        }

    }

    public void sendtext(View v) {
        String Pho_num = Phone_Num.getText().toString();
        String country_code_text = CountryCode.getText().toString();
        String phoneNumber = "+" + country_code_text + Pho_num;
        String smsMessage = "GÜVENDEYİM";

        if (phoneNumber.length() == 1 || phoneNumber.length() == 0) {
            return;
        }
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }



    public void deleteNode(View v){
        DocumentReference noteRef = db.collection("Contacts").document("1mJ7E3z7oRJPDhT3Wa6u");
        noteRef.update(KEY_DESCRIPTION, FieldValue.delete());
        noteRef.delete();
        noteRef = db.collection("Contacts").document("Wye8dD9P4Jo0UgWPpdkV");
        noteRef.update(KEY_DESCRIPTION, FieldValue.delete());
        noteRef.delete();
        textViewData.setText("");
    }





    /*public void deleteNode(View v) {
        numberRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }
                assert queryDocumentSnapshots != null;
                List<String> myArray = new ArrayList<>();
                String[] simpleArray = new String[ myArray.size() ];
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Note note = documentSnapshot.toObject(Note.class);
                    note.setDocumentId(documentSnapshot.getId());
                    String documentId = note.getDocumentId();
                    myArray.add(documentId);
                    myArray.toArray( simpleArray );
                }
                for (int i = 0; i < myArray.size(); i++){
                    db.collection("Contacts").document(simpleArray[i])
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error deleting document", e);
                                }
                            });
                }
            }
        });
    }*/
}
