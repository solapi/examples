import model.request.MessageIds;
import model.response.DeleteGroupModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteGroupMessages {
    public static void main(String[] args) {
        ArrayList<String> messageIds = new ArrayList<>();
        messageIds.add("[Message ID를 입력해주세요]");
        Call<DeleteGroupModel> api = APIInit.getAPI().deleteGroupMessages(APIInit.getHeaders(), "[Group ID를 입력해주세요]", new MessageIds(messageIds));
        api.enqueue(new Callback<DeleteGroupModel>() {
            @Override
            public void onResponse(Call<DeleteGroupModel> call, Response<DeleteGroupModel> response) {
                // 성공시 200이 출력됩니다.
                System.out.println(response.code());
                try {
                    System.out.println(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DeleteGroupModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
