package com.example.vestsafer;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BluetoothRequest extends StringRequest {
    final static private String URL = "http://vestsafer.dothome.co.kr/bluetooth.php";
    private Map<String, String> parameters;

    public BluetoothRequest(String userID, String str_btn,String str_dust,String str_co,String str_hum,String str_temp,Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("str_btn", str_btn);
        parameters.put("str_dust", str_dust);
        parameters.put("str_co", str_co);
        parameters.put("str_hum",str_hum);
        parameters.put("str_temp",str_temp);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
