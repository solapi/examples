import model.request.ScheduleDate;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ScheduleGroupMessage {
    public static void main(String[] args) throws ParseException {
        // 전송하실 일시를 입력하세요. ex) 2018-10-10 14:39:03
        String dateString = "[전송하실 일시를 입력하세요]";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);

        Call<ResponseBody> api = APIInit.getAPI().scheduleGroupMessage(APIInit.getHeaders(), "[Group ID를 입력하세요]", new ScheduleDate(nowAsISO));
        api.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // 성공시 200이 출력됩니다.
                System.out.println(response.code());
                try {
                    // 성공 시 "Success" 가 출력됩니다.
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
