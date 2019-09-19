import model.response.GroupModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMessageGroupInfo {
    public static void main(String[] args) {
        Call<GroupModel> api = APIInit.getAPI().getGroupInfo(APIInit.getHeaders(), "[Group ID를 입력하세요]");
        getGroupInfo(api);
    }

    static void getGroupInfo(Call<GroupModel> api) {
        api.enqueue(new Callback<GroupModel>() {
            @Override
            public void onResponse(Call<GroupModel> call, Response<GroupModel> response) {
                System.out.println(response.code());
                GroupModel body = response.body();
                System.out.println("agent : " + body.getAgent());
                System.out.println("count : " + body.getCount());
                System.out.println("log : " + body.getLog());
                System.out.println("status : " + body.getStatus());
                System.out.println("accountId : " + body.getAccountId());
                System.out.println("apiVersion : " + body.getApiVersion());
                System.out.println("groupId : " + body.getGroupId());
                System.out.println("dateCreated : " + body.getDateCreated());
                System.out.println("dateUpdated : " + body.getDateUpdated());
                System.out.println("_id : " + body.get_id());
            }

            @Override
            public void onFailure(Call<GroupModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
