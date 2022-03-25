package com.live.programming.an15_notebook.users;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.live.programming.an15_notebook.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();

        proceedForLogin();
    }

    private void proceedForLogin() {
        /* code for  performing proper login */

        fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isComplete()){
                        // if you had made the additional check
                        if(task.getResult().getUser().isEmailVerified())
                        {
                            // follow the process
                        }

                        // notify the success
                        // route the user to the next screen
                    }else
                    {
                        // notify the user either to create a acc or to check for the provided details
                    }
                })
                .addOnFailureListener(e->e.toString());

    }
}






















