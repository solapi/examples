import model.request.ImageModel;
import model.response.ImageResult;
import org.apache.commons.codec.binary.Base64;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.FileInputStream;

public class CreateImages {
    public static void main(String[] args) {
        File imgFile = new File("src/image/testImage.jpg");
        long length = imgFile.length();
        byte[] imageByte = new byte[(int) length];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imgFile);
            fis.read(imageByte);
        } catch (Exception e) {
//            혹시 FileNotFoundException이 뜬다면 아래의 코드로 경로를 확인해보고 맞추시면 됩니다.
//            System.out.println(new File("src/image/testImage.jpg").getAbsoluteFile());
            e.printStackTrace();
        }
        String result = new String(Base64.encodeBase64(imageByte));
        Call<ImageResult> api = APIInit.getImageAPI().createImage(APIInit.getHeaders(), new ImageModel(result));
        api.enqueue(new Callback<ImageResult>() {
            @Override
            public void onResponse(Call<ImageResult> call, Response<ImageResult> response) {
                System.out.println(response.code());
                // 성공시에 M4V로 시작하는 imageId가 넘어옵니다.
                if (response.isSuccessful()) {
                    System.out.println("imageId: " + response.body().getImageId());
                } else {
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<ImageResult> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
