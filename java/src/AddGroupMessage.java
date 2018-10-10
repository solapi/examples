import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.request.MessageList;
import model.response.MessageListRes;
import model.response.MessageRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;

public class AddGroupMessage {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("to", "01029951047");
        jsonObject.addProperty("from", "01029951047");
        jsonObject.addProperty("text", "01029951047");
        ArrayList<JsonObject> jsonObjects = new ArrayList<>();
        jsonObjects.add(jsonObject);
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonObjects);

        Call<MessageListRes> api = APIInit.getAPI().addGroupMessage(APIInit.getHeaders(), "G4V20181010104753WODJ8GIAFYJ93GH", new MessageList(jsonString));
        api.enqueue(new Callback<MessageListRes>() {
            @Override
            public void onResponse(Call<MessageListRes> call, Response<MessageListRes> response) {
                MessageListRes body = response.body();
                System.out.println("errorCount : " + body.getErrorCount());
                for (MessageRes message : body.getResultList()) {
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
            }

            @Override
            public void onFailure(Call<MessageListRes> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
