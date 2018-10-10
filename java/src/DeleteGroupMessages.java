import model.request.MessageIds;
import model.response.DeleteGroupRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;

public class DeleteGroupMessages {
    public static void main(String[] args) {
        ArrayList<String> messageIds = new ArrayList<>();
        messageIds.add("M4V20181010104828WDK1PWF32SSIVQR");
        Call<DeleteGroupRes> api = APIInit.getAPI().deleteGroupMessages(APIInit.getHeaders(), "G4V20181010104753WODJ8GIAFYJ93GH", new MessageIds(messageIds));
        api.enqueue(new Callback<DeleteGroupRes>() {
            @Override
            public void onResponse(Call<DeleteGroupRes> call, Response<DeleteGroupRes> response) {
                // 성공시 200이 출력됩니다.
                System.out.println(response.code());
            }

            @Override
            public void onFailure(Call<DeleteGroupRes> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
