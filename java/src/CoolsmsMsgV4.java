import model.request.Message;
import model.response.GroupListRes;
import model.response.GroupRes;
import model.response.MessageRes;
import retrofit2.Call;
import retrofit2.http.*;

public interface CoolsmsMsgV4 {
    @POST("/messages/v4/send")
    Call<MessageRes> sendMessage(@Header("Authorization") String auth,
                                 @Body Message message);

    @POST("/messages/v4/groups")
    Call<GroupRes> createGroup(@Header("Authorization") String auth);

    @GET("/messages/v4/groups")
    Call<GroupListRes> getGroups(@Header("Authorization") String auth);

    @GET("/messages/v4/groups/{groupId}")
    Call<GroupRes> getGroupInfo(@Header("Authorization") String auth,
                                    @Path("groupId") String groupId);
}
