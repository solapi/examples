import model.request.Message;
import model.response.MessageRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sendMessage {
    public static void main(String[] args) {
        Message message =  new Message("01029951047", "01029951047", "test");
        Call<MessageRes> api = APIInit.getAPI().sendMessage(APIInit.getHeaders(), message);
        api.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<MessageRes> call, Response<MessageRes> response) {
                MessageRes body = response.body();
                System.out.println(response.code());
                System.out.println(body.getGroupId());
                System.out.println(body.getMessageId());
                System.out.println(body.getTo());
                System.out.println(body.getFrom());
                System.out.println(body.getType());
                System.out.println(body.getStatusCode());
                System.out.println(body.getStatusMessage());
                System.out.println(body.getCustomFields());
            }

            @Override
            public void onFailure(Call<MessageRes> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
