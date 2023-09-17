package io.cordova.configurer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Base64;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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

    static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJbKeBPMteLlgnkmhfuAJn5CCr+hsRQ+INC6ihCKWrTyGNiM7kxuoFga1cbJIffBekwcbKDejfSrwl42sI12ulDyPSSMIYI3vs0p8rj8rDWUk5KyoVFUcmg+rotlhf0CXoc3dkYPnnmVXy/ZVY1QkHdjsRtpEzi58sATJfnTVhtlAgMBAAECgYEAk/7P747hjXn4nnNi2L6kCRNEfWRy6g04I+SRkT5ZXHKoVBGt6LJDCXX9DwdlIUdxgrX7H8COzu6s6879ZTp8DWTDJNcO6lAaNM1+esJaCM7Lk/Td4qaFKA13F5QIXmHl5I6k4DQRRwMch8M6xln+ufOpZ04xV7h3vxSGYM4ssJkCQQDib5hKB/G4cO47OS/9EIgJaNf8+pht+LmkRwWLOk5PIKgesTxzLupif12W9LbjyRoXQzmJDif/OYcEA1NrAg1/AkEAqnqC4W1ei2a327opuPXcCtfOuiIioXabcvNHj8ZX96wbZjLJNhjEiWy3ied9Y8mmL7A7jbIE/0bZH+zPw+bRGwJAa+/WIDP5PXpXlVOFVJR0xEd97dFmp8VQeMaCFgQNIBrpCPhjYGSUxa8A5dxw8dQC4+PfoT1mQ3sgqQEM2o138QJBAKlOLjku4xxSkPn48oKsOTHTjY0xZW8xWcRZc5KICsj/9d4TRd0hz94jSEv+yHqXwyHNRfe8+lWDyRJ5nwURcE0CQBh/57XXDVYwwiTus6jv3Ugh/GyZa03v9UPY6DwJrz0b1HY3zY56KRAx/hiDpkLA+q5HWyBma52vMeKQgqnI2vU=";

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
            AssetManager assets = cordova.getActivity().getApplicationContext().getAssets();
            InputStream is = assets.open("www/init.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String data = readFile(reader);
            createAndWriteDataFile(file, data);
        }
        String body = readFile(file);
        if (body.length() == 0) {
            body = "{}";
        }
        LOG.i("config", "conf body=" + body);
        return "{ \"code\": 0, \"body\":" + body + "}";
    }

    private String readFile(File file) throws IOException {
        return readFile(new FileReader(file));
    }

    private String readFile(Reader fileReader) throws IOException {
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
