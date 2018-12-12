import model.response.DeleteImageResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteImage {
    public static void main(String[] args) {
        Call<DeleteImageResult> api = APIInit.getImageAPI().deleteImageInfo(APIInit.getHeaders(), "[IMAGE_ID를 입력하세요]");
        api.enqueue(new Callback<DeleteImageResult>() {
            @Override
            public void onResponse(Call<DeleteImageResult> call, Response<DeleteImageResult> response) {
                // 성공 시 200과 SUCCESS가 출력됩니다.
                System.out.println(response.code());
                System.out.println(response.body().getResult());
            }

            @Override
            public void onFailure(Call<DeleteImageResult> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
