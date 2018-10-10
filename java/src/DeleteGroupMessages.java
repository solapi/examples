import model.request.MessageIds;
import model.response.DeleteGroupRes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteGroupMessages {
    public static void main(String[] args) {
        ArrayList<String> messageIds = new ArrayList<>();
        messageIds.add("M4V201810100928156QIYAITN90OL6HO");
        Call<DeleteGroupRes> api = APIInit.getAPI().deleteGroupMessages(APIInit.getHeaders(), "G4V20181010090731TRWHZ2WNIF9LJI3", new MessageIds(messageIds));
        api.enqueue(new Callback<DeleteGroupRes>() {
            @Override
            public void onResponse(Call<DeleteGroupRes> call, Response<DeleteGroupRes> response) {
                // 성공시 200이 출력됩니다.
                System.out.println(response.code());
                try {
                    System.out.println(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DeleteGroupRes> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
