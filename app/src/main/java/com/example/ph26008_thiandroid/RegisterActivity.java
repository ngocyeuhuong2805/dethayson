package com.example.ph26008_thiandroid;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText ed_email, ed_passwd;
    private Button btnRegister1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        ed_email = findViewById(R.id.ed_email);
        ed_passwd = findViewById(R.id.ed_passwd);
        btnRegister1 = findViewById(R.id.btnRegister1);
        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });



    }
    public void onClickRegister(){
        String strEmail = ed_email.getText().toString().trim();
        String strPasswd = ed_passwd.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(strEmail, strPasswd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegisterActivity.this, "ok", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "fare", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}
