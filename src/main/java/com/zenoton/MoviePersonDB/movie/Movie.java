package com.zenoton.MoviePersonDB.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "movie", catalog = "MoviePersonDB",schema = "dbo")
@Component
public class Movie {
    @Autowired
    @Value("${zenoton.MovieDataFetcher}")
    MovieDataFetcher movieDataFetcher;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String name;

    @Column()
    private int UPC;

    @Column
    @Enumerated(EnumType.STRING)
    private PAL palRegion;

    @Column
    private String director;
    @Column
    private String title;
    @Column
    private int year;

    //Determined by the length of the UPC
    private Region region;

    public enum Region{
        REGION1,REGION2;
    }
    public enum PAL{

    }
}
