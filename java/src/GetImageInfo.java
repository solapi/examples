import model.response.ImageInfoResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetImageInfo {
    public static void main(String[] args) {
        Call<ImageInfoResult> api = APIInit.getImageAPI().getImageInfo(APIInit.getHeaders(), "[IMAGE_ID를 입력하세요]");
        api.enqueue(new Callback<ImageInfoResult>() {
            @Override
            public void onResponse(Call<ImageInfoResult> call, Response<ImageInfoResult> response) {
                System.out.println(response.code());
                System.out.println("imageId : " + response.body().getImageId());
                System.out.println("fileName : " + response.body().getFileName());
                System.out.println("fileSize : " + response.body().getFileSize());
            }

            @Override
            public void onFailure(Call<ImageInfoResult> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
