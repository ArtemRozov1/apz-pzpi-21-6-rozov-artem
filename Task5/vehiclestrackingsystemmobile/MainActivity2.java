package vehiclestrackingsystemmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.vehiclestrackingsystemmobile.R;

import vehiclestrackingsystemmobile.api.ApiService;
import vehiclestrackingsystemmobile.api.RetrofitClient;
import vehiclestrackingsystemmobile.models.Transportation;
import vehiclestrackingsystemmobile.adapters.TransportationAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransportationAdapter adapter;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerViewTransportations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadTransportations();
    }

    private void loadTransportations() {
        ApiService apiService = RetrofitClient.getInsecureClient().create(ApiService.class);
        Call<List<Transportation>> call = apiService.getTransportations();

        call.enqueue(new Callback<List<Transportation>>() {
            @Override
            public void onResponse(@NonNull Call<List<Transportation>> call, @NonNull Response<List<Transportation>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Transportation> transportations = response.body();
                    adapter = new TransportationAdapter(transportations);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity2.this, "Failed to load transportations", Toast.LENGTH_SHORT).show();
                    System.out.println("Failed to load transportations");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Transportation>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity2.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", "Error: ", t);
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