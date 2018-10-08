import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.codec.binary.Hex;
import org.ini4j.Ini;
import retrofit2.Retrofit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

class APIInit {
    private static Retrofit retrofit;
    private static CoolsmsMsgV4 service;

    static String getHeaders() {
        try {
            Ini ini = new Ini(new File(APIInit.class.getResource("").getPath() + "/config.ini"));
            String apiKey = ini.get("AUTH", "ApiKey");
            String apiSecret = ini.get("AUTH", "ApiSecret");
            String salt = UUID.randomUUID().toString().replaceAll("-", "");
            String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString().substring(0, 32);

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String signature = new String(Hex.encodeHex(sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8))));
            return "HMAC-SHA256 ApiKey=" + apiKey + ", Date=" + date + ", salt=" + salt + ", signature=" + signature;
        } catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static CoolsmsMsgV4 getAPI() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            Request 시 로그가 필요하면 추가하세요.
//            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://rest.coolsms.co.kr/")
                    .client(client)
                    .build();
            service = retrofit.create(CoolsmsMsgV4.class);
        }
        return service;
    }
}
