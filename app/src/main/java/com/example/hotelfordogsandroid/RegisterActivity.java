package com.example.hotelfordogsandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private MaterialEditText emailTxt;
    private MaterialEditText passwordTxt;
    private MaterialEditText confirmPasswordTxt;
    private TextView errorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.email);
        passwordTxt = findViewById(R.id.password);
        errorTxt = findViewById(R.id.error_txt);
        confirmPasswordTxt = findViewById(R.id.confirm_password);
    }

    public void registerButton(View view) {
        if (!emailTxt.getText().toString().isEmpty() && !passwordTxt.getText().toString().isEmpty() && !confirmPasswordTxt.getText().toString().isEmpty()){
            if (confirmPasswordTxt.getText().toString().equals(passwordTxt.getText().toString())) {
                mAuth.createUserWithEmailAndPassword(emailTxt.getText().toString().trim(), passwordTxt.getText().toString().trim())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this,"Account Created!", Toast.LENGTH_LONG).show();
                                Intent gotoLoginActivity = new Intent(this, LoginActivity.class);
                                this.startActivity(gotoLoginActivity);
                                this.finish(); // do I want this here??
                            } else {
                                errorTxt.setText(task.getException().getMessage());
                            }
                        });
            } else {
                errorTxt.setText("Passwords do not match");
            }
        } else {
            errorTxt.setText("All Fields Required");
        }
    }
}