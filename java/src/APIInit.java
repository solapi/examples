import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.codec.binary.Hex;
import retrofit2.Retrofit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

class APIInit {
    private static Retrofit retrofit;
    private static CoolsmsMsgV4 service;
    private APIInit() {
    }

    static String getHeaders() {
        try {
            String apiKey = "NCSUHUHHBKU6VCPZ";
            String apiSecret = "FYWDIB97LRIW4K55EWTCVG6HWJKAH57K";
            String salt = UUID.randomUUID().toString().replaceAll("-", "");
            String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString();

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String signature = new String(Hex.encodeHex(sha256_HMAC.doFinal((date + salt).getBytes("UTF-8"))));
            return "HMAC-SHA256 ApiKey="+apiKey+", Date=" + date + ", salt=" + salt + ", signature=" + signature;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    static CoolsmsMsgV4 getAPI() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(chain -> {
                System.out.println(getHeaders());
                Request request = chain.request().newBuilder().addHeader("Authorization", getHeaders()).build();
                return chain.proceed(request);
            });
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://rest.coolsms.co.kr/messages/v4/")
                    .client(httpClient.build())
                    .build();
            service = retrofit.create(CoolsmsMsgV4.class);
        }

        return service;
    }
}
