package com.example.fnsConverter.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class Converters {

    public JSONObject createJSONObject(Map<String, String> map){
        return new JSONObject(map);
    }

    public Map<String, String> jsonToMap(JSONObject jsonObject) {
        Map<String, String> map = new HashMap<>();
        Iterator<String> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            String value = (String) jsonObject.get(key);
            map.put(key, value);
        }
        return map;
    }
}
