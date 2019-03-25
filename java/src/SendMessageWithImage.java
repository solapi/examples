import model.request.ImageModel;
import model.request.Message;
import model.response.ImageResult;
import model.response.MessageModel;
import org.apache.commons.codec.binary.Base64;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SendMessageWithImage {
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
                System.out.println("image Status Code: " + response.code());
                // 성공시에 M4V로 시작하는 imageId가 넘어옵니다.
                if (response.isSuccessful()) {
                    String imageId = response.body().getImageId();
                    System.out.println("imageId: " + imageId + "\n");
                    Message message = new Message("[수신번호를 입력하세요]", "[발신번호를 입력하세요]", "[전송할 문자를 입력하세요]", "[전송할 제목을 입력하세요]", imageId);
                    Call<MessageModel> sendApi = APIInit.getAPI().sendMessage(APIInit.getHeaders(), message);
                    sendApi.enqueue(new Callback<MessageModel>() {
                        @Override
                        public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                            if (response.isSuccessful()) {
                                System.out.println("statusCode : " + response.code());
                                MessageModel body = response.body();
                                System.out.println("groupId : " + body.getGroupId());
                                System.out.println("messageId : " + body.getMessageId());
                                System.out.println("to : " + body.getTo());
                                System.out.println("from : " + body.getFrom());
                                System.out.println("type : " + body.getType());
                                System.out.println("statusCode : " + body.getStatusCode());
                                System.out.println("statusMessage : " + body.getStatusMessage());
                                System.out.println("customFields : " + body.getCustomFields());
                            } else {
                                try {
                                    System.out.println(response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MessageModel> call, Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ImageResult> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
