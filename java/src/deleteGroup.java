import model.response.GroupRes;
import retrofit2.Call;

public class deleteGroup {
    public static void main(String[] args) {
        Call<GroupRes> api = APIInit.getAPI().deleteGroupInfo(APIInit.getHeaders(), "G4V20181008152936TIXMLMQUZELVGK9");
        getMessageGroupInfo.getGroupInfo(api);
    }
}
