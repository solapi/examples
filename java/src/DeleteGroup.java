import model.response.GroupModel;
import retrofit2.Call;

public class DeleteGroup {
    public static void main(String[] args) {
        Call<GroupModel> api = APIInit.getAPI().deleteGroupInfo(APIInit.getHeaders(), "G4V20181008152936TIXMLMQUZELVGK9");
        GetMessageGroupInfo.getGroupInfo(api);
    }
}
