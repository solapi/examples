import model.response.GroupRes;
import retrofit2.Call;

public class createMessageGroup {
    public static void main(String[] args) {
        Call<GroupRes> api = APIInit.getAPI().createGroup(APIInit.getHeaders());
        getMessageGroupInfo.getGroupInfo(api);
    }
}
