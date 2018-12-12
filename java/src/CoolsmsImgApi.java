import model.request.*;
import model.response.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

// 문서 : https://docs.coolsms.co.kr/rest-api-reference/image-api
public interface CoolsmsImgApi {
    // 이미지 등록
    @POST("/images/v4/images")
    Call<ImageResult> createImage(@Header("Authorization") String auth,
                                  @Body Image image);

    // 이미지 정보 가져오기
    @GET("/images/v4/images/{imageId}")
    Call<ImageInfoResult> getImageInfo(@Header("Authorization") String auth,
                                  @Path("imageId") String imageId);
}
