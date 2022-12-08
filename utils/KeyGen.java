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

    static String priKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCFJetSdcVgE2gtFW2NutBbb8fI9dE6qgHzt2Ws6MLmMhmpZ1XR4OC/s8L3TsfXH5juS1cUjh/iygXaXJJNExl0l2K0bUtlLydRpgzMU/f+Khz9XRR9X9wNigqPgqrygEp9XmJ9iqul77a9aVQlr1Koy3wlck+zAV2IYlZ/ZQVhKo0VCVCo6ljM9Uh/8Einm/aVuueh61fWfGdzl67tu+Aw9exRcdG9Rribxk5c3z27d0f9eHSdglsyoDRm9pMDgoPr9ObT+efXDge9BnouQS+cTi5uxuS9f++FyltIv//1agtvpLWK9xcpueqod9n9EzRfgYgqKoB867y6UnN0rC+vAgMBAAECggEAJ+yF0jT0GOnYy+Pn9J52iWRMEIr80M1XRn/QYfdtMV8GaNBOYF9ApNq+9C7QJ0YXMiSvGL/A+dy/wokNaUT31gjqlNVMzStFa+sRXVc0PibciHAXi7xpnDmvDvcMUSh8rXjWqXQiIeBt9/+UNrSKYYiaUhQjp7YaGKH787kT0t1g4HsGStdCuT4YFYjXf95GK3fNg2+OyOpjqVNj/v1qlyIYk7ignrlCvNt8jiVDqfbicNzx73JBPn6guJhWLdrqRLvIwpi+rzaSPWsyRNh4R239z0G+dEuxjNs+p5do1oWMgwj3136f81wppg9+wa2kv74cEFkxIGhKyJvIJS3S8QKBgQDnZf+xoYdF7ARfqXww5CzX13cdmSo7Gx+Zw3lJnmEcehegePkHDuDkxuHEcLd/3b33x4+u7u9ubsOLxv78NVSvn9BMCfcj0sp6o0HEA0MeR+eVPPUCuoPK2ukaqclTEF5Rwf1n8lxjvQcfAlI8jSUAjSdm+OYPbALfgCosntl/OQKBgQCTTdQ2Tc43VepxlyRCy+RJkUF4d40DWTZdhsGcZchAavL8yP4ijxuQ0Uf/PQSpW62OsWPou734n4seKuPKXXPMJZuC+7QXClTOdXmgS2USuZSbmfdTwLMxNKWD9ho/VfYte9FYCbo/TJj8/l2536XKfZ/KD0nrg97N+TVwAIo+JwKBgF4L6pw7Vkto3W4z6DHGyyL+g38YHT3gjDtmYwk0watdQ/UItc3UkjrpSiEY3rutV+Q4y4EBw3ZStk89Fa1Tz5OBlJsTAcshhQHLEJOl78WC2/Cf0cTGIYJ07oDTuOt1n6ADBAcXTc2LqjVEMEBD7WIH/JexWW+zKofEA2AXP7ahAoGBAIDNu1RIsJseW0Q8Hw7xbosNoqwODZXx+mHJpZ52OaZqXlLABch+TmJRPZ1n452bdqWhY3VsRO8Twvf8FPcsxEasU9Ey+wRymjl164ZIpva6o8Fz3hq0E3xhRqGfHtBnAD5BWIqc5ujWt6fxXE9dyoDHXGO4/rAbvUVqNlnbfwZdAoGBAKwqc1V5KXPURuj8RU0ynaF94CzTeyiuWKUOE6cfMqfCUm0RZotclRhudksPk/6eEsAOcAj83GrN5huSif0S5JujidFqg4l2v3m0NTHI8eWssc3bCKdQCKNkynuyPkY8jlFNebDmSBmip/pFArRgQnP9Z54NRGl0q17wOdvUlsVS";

    static String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhSXrUnXFYBNoLRVtjbrQW2/HyPXROqoB87dlrOjC5jIZqWdV0eDgv7PC907H1x+Y7ktXFI4f4soF2lySTRMZdJditG1LZS8nUaYMzFP3/ioc/V0UfV/cDYoKj4Kq8oBKfV5ifYqrpe+2vWlUJa9SqMt8JXJPswFdiGJWf2UFYSqNFQlQqOpYzPVIf/BIp5v2lbrnoetX1nxnc5eu7bvgMPXsUXHRvUa4m8ZOXN89u3dH/Xh0nYJbMqA0ZvaTA4KD6/Tm0/nn1w4HvQZ6LkEvnE4ubsbkvX/vhcpbSL//9WoLb6S1ivcXKbnqqHfZ/RM0X4GIKiqAfOu8ulJzdKwvrwIDAQAB";

    public static void main(String[] args) {
        try {
            String code = encode("2022-12-31 18:35:01", pubKey);
            System.out.println(code);
            code = decode(code, priKey);
            // System.out.println(code);
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
