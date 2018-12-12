import model.request.Message;
import model.response.MessageModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMessage {
    public static void main(String[] args) {
        Message message =  new Message("01029951047", "01029951047", "[전송할 문자를 입력하세요]");
//        MMS 전송 시 위의 코드를 제거 또는 주석 처리 후 아래의 코드를 사용하세요
//        Message message =  new Message("01029951047", "01029951047", "[전송할 문자를 입력하세요]", "[IMAGE_ID를 입력하세요]");
        Call<MessageModel> api = APIInit.getAPI().sendMessage(APIInit.getHeaders(), message);
        api.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                // 성공 시 200이 출력됩니다.
                System.out.println("statusCode : " + response.code());
                MessageModel body = response.body();
                System.out.println("groupId : " + body.getGroupId());
                System.out.println("messageId : " + body.getMessageId());
                System.out.println("to : " + body.getTo());
                System.out.println("from : " + body.getFrom());
                System.out.println("type : " + body.getType());
                System.out.println("statusCode : " + body.getStatusCode());
                System.out.println("statusMessage : " + body.getStatusMessage());
                System.out.println("customFields : " + body.getCustomFields());
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
