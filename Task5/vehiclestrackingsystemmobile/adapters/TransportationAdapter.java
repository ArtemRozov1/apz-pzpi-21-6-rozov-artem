package vehiclestrackingsystemmobile.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclestrackingsystemmobile.R;

import vehiclestrackingsystemmobile.api.ApiService;
import vehiclestrackingsystemmobile.api.RetrofitClient;
import vehiclestrackingsystemmobile.models.DriverModel;
import vehiclestrackingsystemmobile.models.Route;
import vehiclestrackingsystemmobile.models.Transportation;
import vehiclestrackingsystemmobile.models.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransportationAdapter extends RecyclerView.Adapter<TransportationAdapter.ViewHolder> {

    private String result = "";

    private final List<Transportation> transportationList;

    public TransportationAdapter(List<Transportation> transportationList) {
        this.transportationList = transportationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transportation, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transportation transportation = transportationList.get(position);
        holder.tvTransportationId.setText("Transportation # " + transportation.getTransportationId());
        holder.tvTransportationDetails.setText("Cargo description: " + transportation.getCargoDescription());
        holder.tvCargoWeight.setText("Cargo weight: " + transportation.getCargoWeight());


        ApiService apiService = RetrofitClient.getInsecureClient().create(ApiService.class);
        Call<Vehicle> call = apiService.getVehicleById(transportation.getVehicleId());

        call.enqueue(new Callback<Vehicle>() {
            @Override
            public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                if (response.isSuccessful()) {
                    Vehicle vehicle = response.body();
                    if (vehicle != null) {
                        Log.d("MainActivity", "Vehicle Number: " + vehicle.getVehicleNumber());
                        holder.tvVehicleId.setText("Vehicle: "
                                + "Number: " + vehicle.getVehicleNumber()
                                + " Manufacturer: " + vehicle.getManufacturer()
                                + " Model: " + vehicle.getModel());
                    } else {
                        Log.e("MainActivity", "Vehicle not found");
                    }
                } else {
                    Log.e("MainActivity", "Request failed");
                }
            }

            @Override
            public void onFailure(Call<Vehicle> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });


        apiService = RetrofitClient.getInsecureClient().create(ApiService.class);
        Call<DriverModel> callDriver = apiService.getDriverById(transportation.getDriverId());

        callDriver.enqueue(new Callback<DriverModel>() {
            @Override
            public void onResponse(Call<DriverModel> call, Response<DriverModel> response) {
                if (response.isSuccessful()) {
                    DriverModel driver = response.body();
                    if (driver != null) {
                        Log.d("MainActivity", "Driver Name: " + driver.getFullName());
                        holder.tvDriverId.setText("Driver: " + driver.getFullName()
                                + " Contact number: " + driver.getContactNumber());
                    } else {
                        Log.e("MainActivity", "Driver not found");
                    }
                } else {
                    Log.e("MainActivity", "Request failed");
                }
            }

            @Override
            public void onFailure(Call<DriverModel> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });


        apiService = RetrofitClient.getInsecureClient().create(ApiService.class);
        Call<Route> callRoute = apiService.getRouteById(transportation.getRouteId());

        callRoute.enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                if (response.isSuccessful()) {
                    Route route = response.body();
                    if (route != null) {
                        Log.d("MainActivity", "Route StartPoint: " + route.getStartPoint());
                        holder.tvRouteId.setText("Route: " + route.getRouteId()
                                + " From: " + route.getStartPoint()
                                + " To: " + route.getEndPoint());
                    } else {
                        Log.e("MainActivity", "Route not found");
                    }
                } else {
                    Log.e("MainActivity", "Request failed");
                    System.out.println("Erorr 11111111 = " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return transportationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTransportationId, tvTransportationDetails, tvCargoWeight, tvDriverId,
                tvVehicleId, tvRouteId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTransportationId = itemView.findViewById(R.id.tvTransportationId);
            tvTransportationDetails = itemView.findViewById(R.id.tvTransportationDetails);
            tvCargoWeight = itemView.findViewById(R.id.tvTransportationWeight);
            tvDriverId = itemView.findViewById(R.id.tvTransportationDriver);
            tvVehicleId = itemView.findViewById(R.id.tvTransportationVehicle);
            tvRouteId = itemView.findViewById(R.id.tvTransportationRoute);
        }
    }
}