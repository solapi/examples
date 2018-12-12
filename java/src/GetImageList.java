import model.response.ImageListItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

public class GetImageList {
    public static void main(String[] args) {
        Call<ArrayList<ImageListItem>> api = APIInit.getImageAPI().getImageList(APIInit.getHeaders());
        api.enqueue(new Callback<ArrayList<ImageListItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ImageListItem>> call, Response<ArrayList<ImageListItem>> response) {
                System.out.println(response.code());
                for(ImageListItem resultItem : response.body()) {
                    System.out.println("accountId : " + resultItem.getAccountId());
                    System.out.println("imageId : " + resultItem.getImageId());
                    System.out.println("fileName : " + resultItem.getFileName());
                    System.out.println("fileSize : " + resultItem.getFileSize());
                    System.out.println("delflag : " + resultItem.getDelFlag());
                    System.out.println("dateCreated : " + resultItem.getDateCreated());
                    System.out.println("dateUpdated : " + resultItem.getDateUpdated());
                    System.out.println();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ImageListItem>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
