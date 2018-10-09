import model.response.GroupRes;
import retrofit2.Call;

public class CreateMessageGroup {
    public static void main(String[] args) {
        Call<GroupRes> api = APIInit.getAPI().createGroup(APIInit.getHeaders());
        GetMessageGroupInfo.getGroupInfo(api);
    }
}
