/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longbnh.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author DELL
 */
public class EncodeHelper {

    public static String HMACSHA256(String signature) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256 = Mac.getInstance("HmacSHA256");
        String moMoSecretKey = "QOwuSfKuPafnaufDHlVAOR3FFPduvyVy";
        SecretKeySpec secret_key = new SecretKeySpec(moMoSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256.init(secret_key);
        return HexBin.encode(sha256.doFinal(signature.getBytes(StandardCharsets.UTF_8))).toLowerCase();
    }
    
    
}
