import model.response.GroupListModel;
import model.response.GroupModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Map;

public class GetGroups {
    public static void main(String[] args) {
        Call<GroupListModel> api = APIInit.getAPI().getGroups(APIInit.getHeaders());
        api.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GroupListModel> call, Response<GroupListModel> response) {
                System.out.println(response.code());
                GroupListModel body = response.body();
                Map<String, GroupModel> groupList = body.getGroupList();
                for (String key : groupList.keySet()) {
                    GroupModel group = groupList.get(key);
                    System.out.println("log : " + group.getLog());
                    System.out.println("agent : " + group.getAgent());
                    System.out.println("count : " + group.getCount());
                    System.out.println("accountId : " + group.getAccountId());
                    System.out.println("apiVersion : " + group.getApiVersion());
                    System.out.println("groupId : " + group.getGroupId());
                    System.out.println("dateCreated : " + group.getDateCreated());
                    System.out.println("dateUpdated : " + group.getDateUpdated());
                    System.out.println("_id : " + group.get_id());
                    System.out.println("status : " + group.getStatus() + "\n");
                }
                System.out.println("offset : " + body.getOffset());
                System.out.println("limit : " + body.getLimit());
                System.out.println("hasNext : " + body.getHasNext());
            }

            @Override
            public void onFailure(Call<GroupListModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
