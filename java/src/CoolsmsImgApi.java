import model.request.*;
import model.response.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.ArrayList;

// 문서 : https://docs.coolsms.co.kr/rest-api-reference/image-api
public interface CoolsmsImgApi {
    // 이미지 등록
    @POST("/images/v4/images")
    Call<ImageResult> createImage(@Header("Authorization") String auth,
                                  @Body ImageModel image);

    // 이미지 정보 가져오기
    @GET("/images/v4/images/{imageId}")
    Call<ImageInfoResult> getImageInfo(@Header("Authorization") String auth,
                                       @Path("imageId") String imageId);

    // 이미지 리스트 가져오기
    @GET("/images/v4/images")
    Call<ArrayList<ImageListItem>> getImageList(@Header("Authorization") String auth);

    // 이미지 삭제
    @DELETE("/images/v4/images/{imageId}")
    Call<DeleteImageResult> deleteImageInfo(@Header("Authorization") String auth,
                                       @Path("imageId") String imageId);
}
