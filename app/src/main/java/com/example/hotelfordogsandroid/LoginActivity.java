package com.example.hotelfordogsandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private MaterialEditText emailTxt;
    private MaterialEditText passwordTxt;
    private TextView errorTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.email);
        passwordTxt = findViewById(R.id.password);
        errorTxt = findViewById(R.id.error_txt);
    }

    public void loginButton(View view) {
        if (!emailTxt.getText().toString().isEmpty() && !passwordTxt.getText().toString().isEmpty()) {
            mAuth.signInWithEmailAndPassword(emailTxt.getText().toString().trim(), passwordTxt.getText().toString().trim())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this,emailTxt.getText().toString() + " is logged in!", Toast.LENGTH_LONG).show();
                            Intent gotoForumActivity = new Intent(this, ForumActivity.class);
                            gotoForumActivity.putExtra("email", emailTxt.getText().toString());
                            this.startActivity(gotoForumActivity);
                        } else {
                            errorTxt.setText(task.getException().getMessage());
                        }
                    });
        } else {
            errorTxt.setText("All Fields Required");
        }
    }

    public void registerButton(View view) {
        Intent gotoRegisterActivity = new Intent(this, RegisterActivity.class);
        this.startActivity(gotoRegisterActivity);
    }
}