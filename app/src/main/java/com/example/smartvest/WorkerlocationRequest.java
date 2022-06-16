package com.example.smartvest;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class WorkerlocationRequest extends StringRequest {
    final static private String URL = "http://smartvest.dothome.co.kr/Location.php";
    private Map<String, String> parameters;

    public WorkerlocationRequest(String userID, String str_datetime, String str_latitude, String str_longitude, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("str_datetime", str_datetime);
        parameters.put("str_latitude", ""+str_latitude);
        parameters.put("str_longitude", ""+str_longitude);

    }


    @Override
    public Map<String, String> getParams() {
        return parameters;
    }

}
