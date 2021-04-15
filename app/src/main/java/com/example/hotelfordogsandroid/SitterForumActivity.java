package com.example.hotelfordogsandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class SitterForumActivity extends AppCompatActivity {

    private MaterialEditText title;
    private MaterialEditText breedSize;
    private MaterialEditText fencedInBackYard;
    private MaterialEditText otherAnimals;
    private MaterialEditText amountPerHour;
    private MaterialEditText phoneNumber;
    private MaterialEditText fullName;
    private EditText sitterBio;
    private TextView errorTxt;
    private FirebaseFirestore db;
    private String email;
    private String city;
    private String state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_forum);
        db = FirebaseFirestore.getInstance();
        email = getIntent().getStringExtra("email");
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");
        title = findViewById(R.id.title);
        breedSize = findViewById(R.id.dog_breed);
        fencedInBackYard = findViewById(R.id.fenced_backyard);
        otherAnimals = findViewById(R.id.other_animals);
        amountPerHour = findViewById(R.id.amount_per_hour);
        phoneNumber = findViewById(R.id.phone);
        fullName = findViewById(R.id.full_name);
        sitterBio = findViewById(R.id.bio);
        errorTxt = findViewById(R.id.error_txt);
    }

    public void postButton(View view) {
        if(errorCheck(title.getText().toString(), breedSize.getText().toString(), fencedInBackYard.getText().toString(), otherAnimals.getText().toString(),
                amountPerHour.getText().toString(), phoneNumber.getText().toString(), fullName.getText().toString(), sitterBio.getText().toString())){

            String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            Map<String, Object> sitterPost = new HashMap<>();
            sitterPost.put("title", title.getText().toString());
            sitterPost.put("breedSize", breedSize.getText().toString());
            sitterPost.put("otherAnimals", otherAnimals.getText().toString());
            sitterPost.put("fencedBackYard", fencedInBackYard.getText().toString());
            sitterPost.put("amountPerHour", amountPerHour.getText().toString());
            sitterPost.put("phone", phoneNumber.getText().toString());
            sitterPost.put("fullName", fullName.getText().toString());
            sitterPost.put("bio", sitterBio.getText().toString());
            sitterPost.put("email", email);
            sitterPost.put("city", city.toLowerCase().trim());
            sitterPost.put("state", state.toLowerCase().trim());
            sitterPost.put("date", currentDate);

            db.collection("sitterPosts")
                    .add(sitterPost)
                    .addOnSuccessListener(documentReference -> navigate())
                    .addOnFailureListener(e -> Toast.makeText(this,"failed to write document! Check connection!", Toast.LENGTH_LONG).show());
        }
    }

    public boolean errorCheck(String title, String breedSize, String fencedInBackYard, String otherAnimals, String amountPerHour, String phoneNumber, String fullName, String sitterBio) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(breedSize) || TextUtils.isEmpty(fencedInBackYard) || TextUtils.isEmpty(otherAnimals) ||
                TextUtils.isEmpty(amountPerHour) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(sitterBio)) {
            errorTxt.setText("All Fields Required!");
            return false;
        } else if (!fencedInBackYard.toLowerCase().equals("yes") && !fencedInBackYard.toLowerCase().equals("no")) {
            errorTxt.setText("Fenced in back yard is asking if you have a fenced in back yard or not. Please enter yes or no. If no please give specifics in the bio");
            return false;
        } else if (!otherAnimals.toLowerCase().equals("yes") && !otherAnimals.toLowerCase().equals("no")){
            errorTxt.setText("Other animals is asking if you have other animals in your house. Please enter yes or no. If no please give specifics in the bio");
            return false;
        } else if (!Pattern.compile("\\$\\d+(?:\\.\\d+)?").matcher(amountPerHour).matches()) {
            errorTxt.setText("error must enter a valid dollar amount. Example $10.50");
            return false;
        } else if (!breedSize.toLowerCase().equals("any") && !breedSize.toLowerCase().equals("small") &&
                   !breedSize.toLowerCase().equals("medium") &&  !breedSize.toLowerCase().equals("large")) {
            errorTxt.setText("Must enter any, small, medium, or large for breed size willing to watch");
            return false;
        } else if (!Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$").matcher(phoneNumber).matches()) {
            errorTxt.setText("Please enter a valid phone number. Example: 123-456-7890");
            return false;
        } else {
            errorTxt.setText("");
            return true;
        }
    }


    public void navigate() {
        Intent gotoForumActivity = new Intent(this, ForumActivity.class);
        gotoForumActivity.putExtra("email", email);
        this.startActivity(gotoForumActivity);
    }
}