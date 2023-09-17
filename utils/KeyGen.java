import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class KeyGen {

    static String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJbKeBPMteLlgnkmhfuAJn5CCr+hsRQ+INC6ihCKWrTyGNiM7kxuoFga1cbJIffBekwcbKDejfSrwl42sI12ulDyPSSMIYI3vs0p8rj8rDWUk5KyoVFUcmg+rotlhf0CXoc3dkYPnnmVXy/ZVY1QkHdjsRtpEzi58sATJfnTVhtlAgMBAAECgYEAk/7P747hjXn4nnNi2L6kCRNEfWRy6g04I+SRkT5ZXHKoVBGt6LJDCXX9DwdlIUdxgrX7H8COzu6s6879ZTp8DWTDJNcO6lAaNM1+esJaCM7Lk/Td4qaFKA13F5QIXmHl5I6k4DQRRwMch8M6xln+ufOpZ04xV7h3vxSGYM4ssJkCQQDib5hKB/G4cO47OS/9EIgJaNf8+pht+LmkRwWLOk5PIKgesTxzLupif12W9LbjyRoXQzmJDif/OYcEA1NrAg1/AkEAqnqC4W1ei2a327opuPXcCtfOuiIioXabcvNHj8ZX96wbZjLJNhjEiWy3ied9Y8mmL7A7jbIE/0bZH+zPw+bRGwJAa+/WIDP5PXpXlVOFVJR0xEd97dFmp8VQeMaCFgQNIBrpCPhjYGSUxa8A5dxw8dQC4+PfoT1mQ3sgqQEM2o138QJBAKlOLjku4xxSkPn48oKsOTHTjY0xZW8xWcRZc5KICsj/9d4TRd0hz94jSEv+yHqXwyHNRfe8+lWDyRJ5nwURcE0CQBh/57XXDVYwwiTus6jv3Ugh/GyZa03v9UPY6DwJrz0b1HY3zY56KRAx/hiDpkLA+q5HWyBma52vMeKQgqnI2vU=";

    static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCWyngTzLXi5YJ5JoX7gCZ+Qgq/obEUPiDQuooQilq08hjYjO5MbqBYGtXGySH3wXpMHGyg3o30q8JeNrCNdrpQ8j0kjCGCN77NKfK4/Kw1lJOSsqFRVHJoPq6LZYX9Al6HN3ZGD555lV8v2VWNUJB3Y7EbaRM4ufLAEyX501YbZQIDAQAB";

    public static void main(String[] args) {
        try {
            String code = encode("2023-10-31 18:35:01", pubKey);
            System.out.println(code);
            code = decode(code, priKey);
            System.out.println(code);
        } catch (Exception e) {
            System.out.println(e.toString());
            // pass
        }
    }

    static String decode(String code, String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] data = Base64.getDecoder().decode(code);
        byte[] key = Base64.getDecoder().decode(keyStr);
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(key));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    static String encode(String str, String publicStr) throws Exception {
        byte[] key = Base64.getDecoder().decode(publicStr);
        RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(key));
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return new String(Base64.getEncoder().encode(cipher.doFinal(str.getBytes("UTF-8"))));
    }
}
