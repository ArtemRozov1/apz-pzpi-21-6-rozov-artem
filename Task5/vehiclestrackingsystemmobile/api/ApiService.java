package vehiclestrackingsystemmobile.api;

import vehiclestrackingsystemmobile.models.LoginModel;
import vehiclestrackingsystemmobile.models.RegistrationModel;
import vehiclestrackingsystemmobile.models.Transportation;
import vehiclestrackingsystemmobile.models.User;
import vehiclestrackingsystemmobile.models.DriverModel;
import vehiclestrackingsystemmobile.models.Route;
import vehiclestrackingsystemmobile.models.Vehicle;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/users/login")
    Call<User> login(@Body LoginModel loginModel);

    @POST("api/users/register")
    Call<ResponseBody> register(@Body RegistrationModel registrationModel);

    @GET("api/Transportations")
    Call<List<Transportation>> getTransportations();

    @GET("Vehicles/GetVehicleById/{id}")
    Call<Vehicle> getVehicleById(@Path("id") int vehicleId);

    @GET("api/DriversApi/{id}")
    Call<DriverModel> getDriverById(@Path("id") int driverId);

    @GET("Routes/{id}")
    Call<Route> getRouteById(@Path("id") int routeId);
}