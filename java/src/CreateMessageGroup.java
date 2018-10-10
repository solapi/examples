import model.response.GroupModel;
import retrofit2.Call;

public class CreateMessageGroup {
    public static void main(String[] args) {
        Call<GroupModel> api = APIInit.getAPI().createGroup(APIInit.getHeaders());
        GetMessageGroupInfo.getGroupInfo(api);
    }
}
