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
                System.out.println("groupId : " + body.getGroupId());
                System.out.println("getMessageId : " + body.getMessageId());
                System.out.println("to : " + body.getTo());
                System.out.println("from : " + body.getFrom());
                System.out.println("type : " + body.getType());
                System.out.println("statusCode : " + body.getStatusCode());
                System.out.println("statusMessage : " + body.getStatusMessage());
                System.out.println("customFields : " + body.getCustomFields());
            }

            @Override
            public void onFailure(Call<MessageRes> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
