import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CoolsmsMsgV4 {
    @GET("/messages/v4/groups")
    Call<ResponseBody> getGroups(@Header("Authorization") String auth);
}
