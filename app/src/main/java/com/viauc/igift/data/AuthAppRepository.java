package com.viauc.igift.data;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthAppRepository {

    private static AuthAppRepository instance;

    private final Application application;
    private final UserLiveData currentUser;

    // Firebase instances
    private final FirebaseAuth mAuth;
   private final FirebaseFirestore firebaseFirestore;

    // Facebook
    private final CallbackManager facebookCallbackManager;

    private static final String FIREBASE_STORAGE_TAG = "FirebaseStorage";
    private static final String FACEBOOK_TAG = "Facebook";


    private AuthAppRepository(Application application) {
        this.application = application;
        currentUser = new UserLiveData();
        // Initialize firebase instances
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        // Initialize Facebook Callback Manager
        facebookCallbackManager = CallbackManager.Factory.create();
    }

    public static AuthAppRepository getInstance(Application app) {
        if(instance == null)
            instance = new AuthAppRepository(app);
        return instance;
    }

    public void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Store the user in firebase storage
                            FirebaseUser user = mAuth.getCurrentUser();
                            populateFirebaseStorageWithCurrentUser(user);
                            // Store the user in firebase storage
                        } else {
                            // If signup fails, and user emial is already registered.
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                Toast.makeText(application, "The email address is already in use by another account.", Toast.LENGTH_SHORT).show();

                            }
                            // If sign in fails, display a message to the user.
                            Log.w("Sing up", "signInWithEmail:failure", task.getException());
                            Toast.makeText(application, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            checkIfUserExists(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(application, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public CallbackManager getFacebookCallbackManager() {
        return facebookCallbackManager;
    }

    public void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(FACEBOOK_TAG, "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(FACEBOOK_TAG, "signInWithCredential:failure", task.getException());
                                Toast.makeText(application, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }

    /**
     * Check if user exists in firebase storage and in case it does not exist it will be added there
     * @param user
     */
    private void checkIfUserExists(FirebaseUser user) {
        if(user==null){
            return;
        }
        DocumentReference docRef = firebaseFirestore.collection("users").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(FIREBASE_STORAGE_TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(FIREBASE_STORAGE_TAG, "No such document");
                        populateFirebaseStorageWithCurrentUser(user);

                    }
                } else {
                    Log.d(FIREBASE_STORAGE_TAG, "get failed with ", task.getException());
                }
            }
        });

    }

    private void populateFirebaseStorageWithCurrentUser(FirebaseUser user) {
        DocumentReference documentReference = firebaseFirestore.collection("users").document(user.getUid());
        Map<String, Object> userHashMap = new HashMap<>();
        userHashMap.put("email", user.getEmail());
        documentReference.set(userHashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Firebase Storage", "User profile is created for " + user.getUid());
            }
        });
    }



    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(application.getApplicationContext());
    }
}
