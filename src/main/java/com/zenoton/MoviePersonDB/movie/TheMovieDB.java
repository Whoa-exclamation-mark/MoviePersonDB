package com.zenoton.MoviePersonDB.movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

@Component
public class TheMovieDB extends MovieDataFetcher {
    //TODO pull api key from file
    //TODO add caching
    private final APIWrapper GET_MOVIE = new APIWrapper("https://api.themoviedb.org/3/movie/${id}?api_key=${api_key}",apiKey);
    private final APIWrapper GET_MOVIE_ID = new APIWrapper("https://api.themoviedb.org/3/search/movie?api_key=${api_key}&language=en-US&query=${name}&page=1&include_adult=true",apiKey);
    private final APIWrapper GET_MOVIE_RELEASE = new APIWrapper("https://api.themoviedb.org/3/movie/${id}/release_dates?api_key=${api_key}",apiKey);
    private final APIWrapper GET_MOVIE_PICTURE = new APIWrapper("http://image.tmdb.org/t/p/original/${picture_id}","");
    @Override
    public String getName() {
        return GET_MOVIE.getDataFromKey(new HashMap<String, String>(){{
            put("id",getID());
        }},"title").toString();
    }

    @Override
    public Date getYear() {
        return Date.valueOf(GET_MOVIE.getDataFromKey(new HashMap<String, String>(){{
            put("id",getID());
        }},"release_date").toString());
    }

    @Override
    public Rating getRating() {
        try {
            JSONArray jsonArray = (JSONArray) GET_MOVIE_RELEASE.getDataFromKey(new HashMap<String, String>(){{
                put("id",getID());}},"results");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                if(object.get("iso_3166_1")==region)return ratingRepository.findByAbbreviatedName(object.getString("certification"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return ratingRepository.findByAbbreviatedName("M");
    }

    @Override
    public String getImage() {
        return GET_MOVIE_PICTURE.getCompiledURL(new HashMap<String, String>() {{
            put("picture_id", getPictureID());
        }});

    }

    public String getPictureID(){
        return GET_MOVIE.getDataFromKey(new HashMap<String, String>(){{
            put("id",getID());
        }},"poster_path").toString();
    }

    @Override
    //TODO find a way to have users to choose option
    public String getID() {
        try {
            return ((JSONObject)((JSONArray)GET_MOVIE_ID.getDataFromKey(new HashMap<String, String>(){{
                put("name",getUPCName());
            }},"results")).get(0)).get("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "0";
    }

    @Override
    public String getOverview() {
        return GET_MOVIE.getDataFromKey(new HashMap<String, String>(){{
            put("id",getID());
        }},"overview").toString();
    }

    @Override
    public String getIMBdID() {
        return GET_MOVIE.getDataFromKey(new HashMap<String, String>(){{
            put("id",getID());
        }},"imdb_id").toString();
    }

    @Override
    public Time getRuntime() {
        return Time.valueOf(GET_MOVIE.getDataFromKey(new HashMap<String, String>(){{
            put("id",getID());
        }},"runtime").toString());
    }

    @Override
    public String getTagLine() {
        return GET_MOVIE.getDataFromKey(new HashMap<String, String>(){{
            put("id",getID());
        }},"tagline").toString();
    }
}
