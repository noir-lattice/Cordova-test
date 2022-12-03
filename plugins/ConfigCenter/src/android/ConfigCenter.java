package io.cordova.configurer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

class Constants {

    static final String VERIFY_FILE_NAME = ".verify";

    static final String CONFIG_FILE_NAME = "conf.json";

    static final String DEFAULT_CONFIG_BODY = "{}";

    static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCFJetSdcVgE2gtFW2NutBbb8fI9dE6qgHzt2Ws6MLmMhmpZ1XR4OC/s8L3TsfXH5juS1cUjh/iygXaXJJNExl0l2K0bUtlLydRpgzMU/f+Khz9XRR9X9wNigqPgqrygEp9XmJ9iqul77a9aVQlr1Koy3wlck+zAV2IYlZ/ZQVhKo0VCVCo6ljM9Uh/8Einm/aVuueh61fWfGdzl67tu+Aw9exRcdG9Rribxk5c3z27d0f9eHSdglsyoDRm9pMDgoPr9ObT+efXDge9BnouQS+cTi5uxuS9f++FyltIv//1agtvpLWK9xcpueqod9n9EzRfgYgqKoB867y6UnN0rC+vAgMBAAECggEAJ+yF0jT0GOnYy+Pn9J52iWRMEIr80M1XRn/QYfdtMV8GaNBOYF9ApNq+9C7QJ0YXMiSvGL/A+dy/wokNaUT31gjqlNVMzStFa+sRXVc0PibciHAXi7xpnDmvDvcMUSh8rXjWqXQiIeBt9/+UNrSKYYiaUhQjp7YaGKH787kT0t1g4HsGStdCuT4YFYjXf95GK3fNg2+OyOpjqVNj/v1qlyIYk7ignrlCvNt8jiVDqfbicNzx73JBPn6guJhWLdrqRLvIwpi+rzaSPWsyRNh4R239z0G+dEuxjNs+p5do1oWMgwj3136f81wppg9+wa2kv74cEFkxIGhKyJvIJS3S8QKBgQDnZf+xoYdF7ARfqXww5CzX13cdmSo7Gx+Zw3lJnmEcehegePkHDuDkxuHEcLd/3b33x4+u7u9ubsOLxv78NVSvn9BMCfcj0sp6o0HEA0MeR+eVPPUCuoPK2ukaqclTEF5Rwf1n8lxjvQcfAlI8jSUAjSdm+OYPbALfgCosntl/OQKBgQCTTdQ2Tc43VepxlyRCy+RJkUF4d40DWTZdhsGcZchAavL8yP4ijxuQ0Uf/PQSpW62OsWPou734n4seKuPKXXPMJZuC+7QXClTOdXmgS2USuZSbmfdTwLMxNKWD9ho/VfYte9FYCbo/TJj8/l2536XKfZ/KD0nrg97N+TVwAIo+JwKBgF4L6pw7Vkto3W4z6DHGyyL+g38YHT3gjDtmYwk0watdQ/UItc3UkjrpSiEY3rutV+Q4y4EBw3ZStk89Fa1Tz5OBlJsTAcshhQHLEJOl78WC2/Cf0cTGIYJ07oDTuOt1n6ADBAcXTc2LqjVEMEBD7WIH/JexWW+zKofEA2AXP7ahAoGBAIDNu1RIsJseW0Q8Hw7xbosNoqwODZXx+mHJpZ52OaZqXlLABch+TmJRPZ1n452bdqWhY3VsRO8Twvf8FPcsxEasU9Ey+wRymjl164ZIpva6o8Fz3hq0E3xhRqGfHtBnAD5BWIqc5ujWt6fxXE9dyoDHXGO4/rAbvUVqNlnbfwZdAoGBAKwqc1V5KXPURuj8RU0ynaF94CzTeyiuWKUOE6cfMqfCUm0RZotclRhudksPk/6eEsAOcAj83GrN5huSif0S5JujidFqg4l2v3m0NTHI8eWssc3bCKdQCKNkynuyPkY8jlFNebDmSBmip/pFArRgQnP9Z54NRGl0q17wOdvUlsVS";

}

class CodeVerify {

    private final String code;

    CodeVerify(String raw) {
        code = raw;
    }

    String decode() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] data = Base64.decode(code.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        byte[] key = Base64.decode(Constants.PRIVATE_KEY.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(key));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    boolean timeCheck(Date expireTime) throws IOException {
        Date now = new Date();
        URL url = new URL("https://www.baidu.com");
        URLConnection uc = url.openConnection();
        uc.connect();
        LOG.i("verify", "verify req wast=" + (System.currentTimeMillis() - now.getTime()));
        long ld = uc.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ld);
        Date correctTime = calendar.getTime();
        return Math.abs(correctTime.getTime() - now.getTime()) < TimeUnit.SECONDS.toMillis(60) && correctTime.getTime() < expireTime.getTime();
    }

    boolean verify() {
        try {
            String dateStr = decode();
            LOG.i("verify", "verify dataStr=" + dateStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date expireTime = sdf.parse(dateStr);
            return timeCheck(expireTime);
        } catch (Exception e) {
            LOG.e("verify", "verify e=", e);
            e.printStackTrace();
            return false;
        }
    }
}

/**
 * This class echoes a string called from JavaScript.
 */
public class ConfigCenter extends CordovaPlugin {


    private String loadConfig(String filepath) throws IOException {
        if (!verify(filepath)) {
            return retRejectView();
        }
        return retReadFile(filepath);
    }

    private boolean verify(String filepath) throws IOException {
        String verifyFilePath = filepath + Constants.VERIFY_FILE_NAME;
        File file = new File(verifyFilePath);
        if (!file.exists()) {
            return false;
        }
        String code = readFile(file);
        return codeVerify(code);
    }

    private boolean codeVerify(String code) {
        return new CodeVerify(code).verify();
    }

    private String retRejectView() {
        return "{\"code\": -1 }";
    }

    private String retReadFile(String filepath) throws IOException {
        String configFilePath = filepath + Constants.CONFIG_FILE_NAME;
        LOG.i("config", "load file=[" + configFilePath + "]");
        File file = new File(configFilePath);
        if (!file.exists()) {
            createAndWriteDataFile(file, Constants.DEFAULT_CONFIG_BODY);
        }
        String body = readFile(file);
        if (body.length() == 0) {
            body = "{}";
        }
        LOG.i("config", "conf body=" + body);
        return "{ \"code\": 0, \"body\":" + body + "}";
    }

    private String readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        char[] buffer = new char[4096];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = fileReader.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, len));
        }

        return sb.toString();
    }

    private void createAndWriteDataFile(File file, String data) throws IOException {
        if (!file.createNewFile()) {
            throw new IllegalAccessError("create file failed");
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private boolean register(String code, String filepath) throws IOException {
        if (!codeVerify(code)) {
            return false;
        }

        String verifyFilePath = filepath + Constants.VERIFY_FILE_NAME;
        File file = new File(verifyFilePath);
        if (file.exists()) {
            file.delete();
        }
        createAndWriteDataFile(file, code);
        return true;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("check".equals(action)) {
            permissionReq(callbackContext);
            return true;
        }
        String dataDir = this.cordova.getContext().getExternalFilesDir("").getAbsolutePath() + "/";
        if ("get".equals(action)) {
            String res;
            try {
                res = loadConfig(dataDir);
                callbackContext.success(res);
            } catch (Exception e) {
                LOG.e("config", "load e=", e);
                callbackContext.error("load file e=" + e);
            }
            return true;
        }
        if ("register".equals(action)) {
            String code = args.getString(0);
            try {
                boolean status = register(code, dataDir);
                callbackContext.success(status + "");
            } catch (Exception e) {
                LOG.e("register", "register e=", e);
                callbackContext.error("register e=" + e);
            }
            return true;
        }
        return false;
    }

    private void permissionReq(CallbackContext callbackContext) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            callbackContext.error("android版本过低");
        }
        // 先判断有没有权限
        if (!(ActivityCompat.checkSelfPermission(this.cordova.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.cordova.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this.cordova.getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1024);
        }
        callbackContext.success("done");
    }

}
