package com.live.programming.an15_notebook.users;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.live.programming.an15_notebook.models.Accounts;
import com.live.programming.an15_notebook.R;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore fireStore;
    String email, pass, name,   mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        registrationProcess();

    }

    private void registrationProcess() {
        /**
         * code that will help to add user in the auth
         */
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if(task.isComplete()){
                        FirebaseUser fUser = task.getResult().getUser();

                        String userUID = fUser.getUid();
                        /* additional check for the user */

                        fUser.sendEmailVerification();  // send a verification mail

                        storeUserDetails(userUID);
                    }
                })
                .addOnFailureListener(e -> //write code to notify the user
                         e.getMessage());
    }

    private void storeUserDetails(String uid) {
        /*
         * code that will store add the info that user is providing
         */
        Accounts acc = new Accounts(uid, name,email,mobile);

        fireStore.collection("UserAccount")
                .document(uid)
                .set(acc)
                .addOnCompleteListener(task -> {
                    if(task.isComplete())
                    {
                        // display suitable message to user
                        // route to a different screen (to a login screen)
                    }
                })
                .addOnFailureListener(e->e.getMessage());
    }
}