import model.request.*;
import model.response.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

// 문서 : https://docs.coolsms.co.kr/rest-api-reference/image-api
public interface CoolsmsImgApi {
    // 심플 메시지
    @POST("/images/v4/images")
    Call<ImageResult> createImage(@Header("Authorization") String auth,
                                   @Body Image image);
}
