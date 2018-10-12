import model.response.GroupModel;
import retrofit2.Call;

public class DeleteGroup {
    public static void main(String[] args) {
        Call<GroupModel> api = APIInit.getAPI().deleteGroupInfo(APIInit.getHeaders(), "[Group ID를 입력하세요]");
        GetMessageGroupInfo.getGroupInfo(api);
    }
}
