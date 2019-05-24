import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.request.MessageList;
import model.response.AddMessageListModel;
import model.response.MessageModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;

public class AddGroupMessage {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("to", "[수신번호를 입력하세요]");
        jsonObject.addProperty("from", "[발신번호를 입력하세요]");
        jsonObject.addProperty("text", "[전송할 문자를 입력하세요]");
//        MMS 전송 시 아래의 코드를 사용하세요
//        jsonObject.addProperty("imageId", "[IMAGE_ID를 입력하세요]");
        ArrayList<JsonObject> jsonObjects = new ArrayList<>();
        jsonObjects.add(jsonObject);
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonObjects);

        Call<AddMessageListModel> api = APIInit.getAPI().addGroupMessage(APIInit.getHeaders(), "[Group ID를 입력하세요]", new MessageList(jsonString));
        api.enqueue(new Callback<AddMessageListModel>() {
            @Override
            public void onResponse(Call<AddMessageListModel> call, Response<AddMessageListModel> response) {
                AddMessageListModel body = response.body();
                try {
                    System.out.println("errorCount : " + body.getErrorCount());
                    for (MessageModel message : body.getResultList()) {
                        System.out.println("groupId : " + message.getGroupId());
                        System.out.println("messageId : " + message.getMessageId());
                        System.out.println("to : " + message.getTo());
                        System.out.println("from : " + message.getFrom());
                        System.out.println("type : " + message.getType());
                        System.out.println("statusCode : " + message.getStatusCode());
                        System.out.println("statusMessage : " + message.getStatusMessage());
                        System.out.println("customFields : " + message.getCustomFields());
                        System.out.println("country : " + message.getCountry());
                        System.out.println("accountId : " + message.getAccountId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AddMessageListModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
