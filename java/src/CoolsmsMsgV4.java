import model.request.MessageIds;
import model.request.Message;
import model.request.MessageList;
import model.request.ScheduleDate;
import model.response.*;
import model.response.GetMessageListModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface CoolsmsMsgV4 {
    @POST("/messages/v4/send")
    Call<MessageModel> sendMessage(@Header("Authorization") String auth,
                                   @Body Message message);

    @POST("/messages/v4/groups")
    Call<GroupModel> createGroup(@Header("Authorization") String auth);

    @GET("/messages/v4/groups")
    Call<GroupListModel> getGroups(@Header("Authorization") String auth);

    @GET("/messages/v4/groups/{groupId}")
    Call<GroupModel> getGroupInfo(@Header("Authorization") String auth,
                                  @Path("groupId") String groupId);

    @DELETE("/messages/v4/groups/{groupId}")
    Call<GroupModel> deleteGroupInfo(@Header("Authorization") String auth,
                                     @Path("groupId") String groupId);

    @PUT("/messages/v4/groups/{groupId}/messages")
    Call<AddMessageListModel> addGroupMessage(@Header("Authorization") String auth,
                                              @Path("groupId") String groupId,
                                              @Body MessageList messages);

    @POST("/messages/v4/groups/{groupId}/send")
    Call<ResponseBody> sendGroupMessage(@Header("Authorization") String auth,
                                        @Path("groupId") String groupId);

    @POST("/messages/v4/groups/{groupId}/schedule")
    Call<ResponseBody> scheduleGroupMessage(@Header("Authorization") String auth,
                                            @Path("groupId") String groupId,
                                            @Body ScheduleDate scheduleDate);

    @DELETE("/messages/v4/groups/{groupId}/schedule")
    Call<ResponseBody> cancelScheduledGroupMessage(@Header("Authorization") String auth,
                                                   @Path("groupId") String groupId);

    @HTTP(method = "DELETE", path = "/messages/v4/groups/{groupId}/messages", hasBody = true)
    Call<DeleteGroupModel> deleteGroupMessages(@Header("Authorization") String auth,
                                               @Path("groupId") String groupId,
                                               @Body MessageIds messageIds);

    @GET("/messages/v4/list")
    Call<GetMessageListModel> getMessageList(@Header("Authorization") String auth);
}
