package com.zenoton.MoviePersonDB.movie;

import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class APIWrapper {
    private final String URL;
    private final String API_KEY;
    private final String URL_WITH_API_KEY;
    private final int NUMBER_OF_VARIABLES;

    public APIWrapper(String url, String api_key) {
        URL = url;
        API_KEY = api_key;
        if(api_key != ""){
            URL_WITH_API_KEY = URL.replace("${api_key}",API_KEY);
            NUMBER_OF_VARIABLES = StringUtils.countOccurrencesOf(URL,"${")-1;
        }
        else{
            URL_WITH_API_KEY = URL;
            NUMBER_OF_VARIABLES = StringUtils.countOccurrencesOf(URL,"${");
        }
    }

    public JSONObject getJsonObject(Map<String,String> values){
        String compiledURL = getCompiledURL(values);
        String data = getDataFromURL(compiledURL);
        try {
            return new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public Object getDataFromKey(Map<String,String> args,String key){
        try {
            return getJsonObject(args).get(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO add XML

    private String getDataFromURL(String url){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url,String.class);
    }

    public String getCompiledURL(Map<String,String> values){
        return (new StrSubstitutor(values)).replace(URL_WITH_API_KEY);
    }

}
