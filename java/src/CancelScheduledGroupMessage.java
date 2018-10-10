import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelScheduledGroupMessage {
    public static void main(String[] args) {
        Call<ResponseBody> api = APIInit.getAPI().cancelScheduledGroupMessage(APIInit.getHeaders(), "G4V20181010090731TRWHZ2WNIF9LJI3");
        api.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // 성공시 200이 출력됩니다.
                System.out.println(response.code());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
