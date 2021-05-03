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

public class NeedForumActivity extends AppCompatActivity {

    private MaterialEditText title;
    private MaterialEditText dogBreed;
    private MaterialEditText dogName;
    private MaterialEditText animalFriendly;
    private MaterialEditText pottyTrained;
    private MaterialEditText amountPerHour;
    private MaterialEditText amountOfTime;
    private MaterialEditText phoneNumber;
    private MaterialEditText fullName;
    private EditText dogNeeds;
    private TextView errorTxt;
    private FirebaseFirestore db;
    private String email;
    private String city;
    private String state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_forum);
        db = FirebaseFirestore.getInstance();
        email = getIntent().getStringExtra("email");
        city = getIntent().getStringExtra("city");
        state = getIntent().getStringExtra("state");
        title = findViewById(R.id.title);
        dogBreed = findViewById(R.id.dog_breed);
        dogName = findViewById(R.id.dog_name);
        animalFriendly = findViewById(R.id.animal_friendly);
        pottyTrained = findViewById(R.id.potty_trained);
        amountPerHour = findViewById(R.id.amount_per_hour);
        amountOfTime = findViewById(R.id.amount_of_time);
        phoneNumber = findViewById(R.id.phone);
        fullName = findViewById(R.id.full_name);
        dogNeeds = findViewById(R.id.dog_needs);
        errorTxt = findViewById(R.id.error_txt);
    }

    public void postButton(View view) {
        if(errorCheck(title.getText().toString(), dogBreed.getText().toString(), dogName.getText().toString(), animalFriendly.getText().toString(), pottyTrained.getText().toString(),
                amountPerHour.getText().toString(), amountOfTime.getText().toString(), phoneNumber.getText().toString(), fullName.getText().toString(), dogNeeds.getText().toString())){

            String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            Map<String, Object> needPost = new HashMap<>();
            needPost.put("title", title.getText().toString());
            needPost.put("dogBreed", dogBreed.getText().toString());
            needPost.put("dogName", dogName.getText().toString());
            needPost.put("animalFriendly", animalFriendly.getText().toString());
            needPost.put("pottyTrained", pottyTrained.getText().toString());
            needPost.put("amountPerHour", amountPerHour.getText().toString());
            needPost.put("amountOfTime", amountOfTime.getText().toString());
            needPost.put("phone", phoneNumber.getText().toString());
            needPost.put("fullName", fullName.getText().toString());
            needPost.put("dogNeeds", dogNeeds.getText().toString());
            needPost.put("email", email);
            needPost.put("city", city.toLowerCase().trim());
            needPost.put("state", state.toLowerCase().trim());
            needPost.put("date", currentDate);

            db.collection("needPosts")
                    .add(needPost)
                    .addOnSuccessListener(documentReference -> navigate())
                    .addOnFailureListener(e -> Toast.makeText(this,"failed to write document! Check connection!", Toast.LENGTH_LONG).show());
        }
    }

    public boolean errorCheck(String title, String dogBreed, String dogName, String animalFriendly, String pottyTrained,
                              String amountPerHour, String amountOfTime, String phoneNumber, String fullName, String dogNeeds) {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(dogBreed) || TextUtils.isEmpty(dogName) || TextUtils.isEmpty(animalFriendly) || TextUtils.isEmpty(pottyTrained) ||
                TextUtils.isEmpty(amountPerHour) || TextUtils.isEmpty(amountOfTime) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(dogNeeds)) {
            errorTxt.setText("All fields required");
            return false;
        } else if (!animalFriendly.toLowerCase().equals("yes") && !animalFriendly.toLowerCase().equals("no")) {
            errorTxt.setText("Animal friendly is asking if your dog is friendly with other animals. Please enter yes or no. If no please give specifics in the bio");
            return false;
        } else if (!pottyTrained.toLowerCase().equals("yes") && !pottyTrained.toLowerCase().equals("no")) {
            errorTxt.setText("Potty trained is asking if your dog is potty trained. Please enter yes or no. If no please give specifics in the bio");
            return false;
        } else if (!Pattern.compile("\\$\\d+(?:\\.\\d+)?").matcher(amountPerHour).matches()) {
            errorTxt.setText("Must enter a valid dollar amount for Amount Per Hour. Example: $10.50");
            return false;
        } else if (!Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$").matcher(phoneNumber).matches()) {
            errorTxt.setText("Please enter a valid phone number. Example: 123-456-7890");
            return false;
        } else if (!Pattern.compile("^[0-9]*[1-9][0-9]* Hours").matcher(amountOfTime).matches() && !Pattern.compile("^[0-9]*[1-9][0-9]* hours").matcher(amountOfTime).matches()) {
            errorTxt.setText("Amount of time is asking how long you need your dog watched for. Example: 5 Hours");
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