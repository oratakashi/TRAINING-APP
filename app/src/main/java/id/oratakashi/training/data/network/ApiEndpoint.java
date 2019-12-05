package id.oratakashi.training.data.network;

import id.oratakashi.training.data.model.register.ResponseRegister;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndpoint {
    @FormUrlEncoded
    @POST("register")
    Single<ResponseRegister> postRegister(
            @Field("email") String email,
            @Field("name") String nama,
            @Field("password") String password
    );
}
