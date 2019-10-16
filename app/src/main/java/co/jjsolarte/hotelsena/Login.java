package co.jjsolarte.hotelsena;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private FirebaseAuth.AuthStateListener myAuthListener;

    EditText edtNombre, edtEmail, edtContraseña, edtContraseña2;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializer();
        myAuth = FirebaseAuth.getInstance();

        myAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = myAuth.getCurrentUser();
                if (firebaseUser!=null){
                    Toast.makeText(Login.this, "Usuario Logeado", Toast.LENGTH_SHORT).show();
                    goContainer();
                }else{
                    Toast.makeText(Login.this, "Usuario No Logeado", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void userRegister() {
        String email = edtEmail.getText().toString();
        String pass = edtContraseña.getText().toString();

        myAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "Logeado", Toast.LENGTH_SHORT).show();
                    goContainer();
                }else {
                    Toast.makeText(Login.this, "Sorry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goContainer() {
        Intent intent = new Intent(this,Container.class);
        startActivity(intent);
        finish();
    }

    private void inicializer() {
        edtNombre = findViewById(R.id.loginEdtNombre);
        edtEmail = findViewById(R.id.loginEdtEmail);
        edtContraseña = findViewById(R.id.loginEdtContraseña);
        edtContraseña2 = findViewById(R.id.loginEdtContraseña2);
        btnRegistrar = findViewById(R.id.loginBtnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAuth.addAuthStateListener(myAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAuth.removeAuthStateListener(myAuthListener);
    }
}
