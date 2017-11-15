package com.zenoton.MoviePersonDB.movie;


import com.zenoton.MoviePersonDB.db.dao.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

public abstract class MovieDataFetcher {

    @Value("${zenoton.region}")
    public String region;

    @Autowired
    public RatingRepository ratingRepository;

    @Value("${zenoton.APIKey}")
    @NotNull
    protected String apiKey;

    private int UPC;

    public abstract String getName();
    public abstract Date getYear();
    public abstract Rating getRating();
    public abstract String getImage();
    public abstract String getID();
    public abstract String getOverview();
    public abstract String getIMBdID();
    public abstract Time getRuntime();
    public abstract String getTagLine();


    public int getUPC() {
        return UPC;
    }

    public void setUPC(int UPC) {
        this.UPC = UPC;
    }

    public String getUPCName(){
        return "Madagascar";
    }
}
