import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CoolsmsMsgV4 {
    @GET("/")
    Call<ResponseBody> test();

    @GET("/groups")
    Call<ResponseBody> getGroups();
}
