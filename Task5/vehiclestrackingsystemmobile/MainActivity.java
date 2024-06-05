package vehiclestrackingsystemmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclestrackingsystemmobile.R;

import vehiclestrackingsystemmobile.api.ApiService;
import vehiclestrackingsystemmobile.api.RetrofitClient;
import vehiclestrackingsystemmobile.models.LoginModel;
import vehiclestrackingsystemmobile.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin, etPassword;
    private Button btnLogin;
    private TextView registrationButton;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = findViewById(R.id.editTextText);
        etPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.button);
        registrationButton = findViewById(R.id.textView);

        btnLogin.setOnClickListener(view -> {
            String login = etLogin.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (!login.isEmpty() && !password.isEmpty()) {
                loginUser(login, password);
            } else {
                Toast.makeText(MainActivity.this, "Please enter login and password", Toast.LENGTH_SHORT).show();
            }
        });

        registrationButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser(String login, String password) {
        ApiService apiService = RetrofitClient.getInsecureClient().create(ApiService.class);
        LoginModel loginModel = new LoginModel();
        loginModel.setLogin(login);
        loginModel.setPassword(password);

        Call<User> call = apiService.login(loginModel);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Toast.makeText(MainActivity.this, "Login successful: " + user.getLogin(), Toast.LENGTH_SHORT).show();
                    System.out.println("Login successful: " + user.getLogin());

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    System.out.println("Login failed");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("An error occurred: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
        Toast.makeText(this, "onStart called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
        Toast.makeText(this, "onResume called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
        Toast.makeText(this, "onPause called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
        Toast.makeText(this, "onStop called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
        Toast.makeText(this, "onDestroy called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart called");
        Toast.makeText(this, "onRestart called", Toast.LENGTH_SHORT).show();
    }

}