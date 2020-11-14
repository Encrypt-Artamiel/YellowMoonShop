/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package momo;

import com.google.gson.Gson;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import longbnh.util.EncodeHelper;

/**
 *
 * @author DELL
 */
public class MoMoRequestController {

    public static MoMoResponseObject sendRequestToMoMo(String orderID, long total)
            throws NoSuchAlgorithmException, InvalidKeyException, MalformedURLException, IOException {
        Gson gson = new Gson();
        String requestID = orderID;
        String partnerCode = "MOMOYM7M20201113";
        String accessKey = "V8nq9GnGviGbZeI6";
        String returnUrl = "momo";
        String signature = "partnerCode=" + partnerCode + "&accessKey=" + accessKey + "&requestId=" + requestID
                + "&amount=" + total + "&orderId=" + orderID + "&orderInfo=testMoMo&returnUrl=" + returnUrl
                + "&notifyUrl=" + returnUrl + "&extraData=";
        signature = EncodeHelper.HMACSHA256(signature);

        MoMoRequestObject request = new MoMoRequestObject(requestID, String.valueOf(total), orderID, signature);
        request.setAccessKey(accessKey);
        request.setPartnerCode(partnerCode);
        request.setOrderInfo("testMoMo");
        request.setReturnUrl(returnUrl);
        request.setNotifyUrl(returnUrl);
        request.setSignature(signature);
        request.setRequestType("captureMoMoWallet");

        String json = gson.toJson(request);
        byte[] postData = json.getBytes(StandardCharsets.UTF_8);

        URL url = new URL("https://test-payment.momo.vn/gw_payment/transactionProcessor");
        
        MoMoResponseObject response = null;
        HttpURLConnection con = null;
        InputStreamReader rd = null;
        DataOutputStream resOut = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", "" + postData.length);
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.connect();

            resOut = new DataOutputStream(con.getOutputStream());
            resOut.write(postData);

            InputStream res = con.getInputStream();
            rd = new InputStreamReader(res, StandardCharsets.UTF_8);

            response = gson.fromJson(rd, MoMoResponseObject.class);

        } finally {
            if (resOut != null) {
                resOut.close();
            }
            if (rd != null) {
                rd.close();
            }
            if (con != null) {
                con.disconnect();
            }
        }
        return response;
    }
}
