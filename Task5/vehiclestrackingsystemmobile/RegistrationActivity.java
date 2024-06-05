package vehiclestrackingsystemmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclestrackingsystemmobile.R;

import vehiclestrackingsystemmobile.api.ApiService;
import vehiclestrackingsystemmobile.api.RetrofitClient;
import vehiclestrackingsystemmobile.models.RegistrationModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etLogin, etPassword, etEmail;
    private Spinner etUserType;
    private Button btnRegister;
    private TextView loginButton;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etLogin = findViewById(R.id.editText);
        etPassword = findViewById(R.id.editTextPassword);
        etEmail = findViewById(R.id.editTextEmailAddress);
        etUserType = findViewById(R.id.spinner);
        btnRegister = findViewById(R.id.button);
        loginButton = findViewById(R.id.textView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_items, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        etUserType.setAdapter(adapter);

        etUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(RegistrationActivity.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnRegister.setOnClickListener(view -> {
            String login = etLogin.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String userType = etUserType.getSelectedItem().toString().trim();

            if (!login.isEmpty() && !password.isEmpty() && !email.isEmpty() && !userType.isEmpty()) {
                registerUser(login, password, email, userType);
            } else {
                Toast.makeText(RegistrationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser(String login, String password, String email, String userType) {
        ApiService apiService = RetrofitClient.getInsecureClient().create(ApiService.class);
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin(login);
        registrationModel.setPassword(password);
        registrationModel.setEmail(email);
        registrationModel.setUserType(userType);

        Call<ResponseBody> call = apiService.register(registrationModel);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        Toast.makeText(RegistrationActivity.this, "Registration successful: " + responseBody, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegistrationActivity.this, MainActivity2.class);
                        startActivity(intent);
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(RegistrationActivity.this, "An error occurred while reading the response.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(RegistrationActivity.this, "Registration failed: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(RegistrationActivity.this, "An error occurred while reading the error response.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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