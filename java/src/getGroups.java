import model.response.GroupListRes;
import model.response.GroupRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.Map;

public class getGroups {
    public static void main(String[] args) {
        Call<GroupListRes> api = APIInit.getAPI().getGroups(APIInit.getHeaders());
        api.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GroupListRes> call, Response<GroupListRes> response) {
                GroupListRes body = response.body();
                Map<String, GroupRes> groupList = body.getGroupList();
                for (String key : groupList.keySet()) {
                    GroupRes group = groupList.get(key);
                    System.out.println("log : " + group.getLog());
                    System.out.println("agent : " + group.getAgent());
                    System.out.println("count : " + group.getCount());
                    System.out.println("scheduledDate : " + group.getGroupId());
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
            public void onFailure(Call<GroupListRes> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
