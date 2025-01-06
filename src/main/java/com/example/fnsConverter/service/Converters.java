package com.example.fnsConverter.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Converters {

    public JSONObject createJSONObject(Map<String, String> map){
        return new JSONObject(map);
    }
}
