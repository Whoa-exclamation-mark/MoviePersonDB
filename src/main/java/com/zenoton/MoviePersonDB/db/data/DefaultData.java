package com.zenoton.MoviePersonDB.db.data;

import com.zenoton.MoviePersonDB.movie.Rating;
import com.zenoton.MoviePersonDB.security.Privilege;
import com.zenoton.MoviePersonDB.security.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DefaultData extends DataGenerator {
    public DefaultData() {
        super();
    }

    @Override
    protected void generateUsers() {
        Privilege privilege1 = privilegeRepository.findByName("MOVIE_READ_PRIVILEGE");
        Privilege privilege2 = privilegeRepository.findByName("MOVIE_WRITE_PRIVILEGE");
        Rating rating1 = ratingRepository.findByAbbreviatedName("MA15+");
        Rating rating2 = ratingRepository.findByAbbreviatedName("G");

        final User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(encoder.encode("admin"));
        user1.setPrivileges(new HashSet<Privilege>(Arrays.asList(privilege1,privilege2)));
        user1.setRating(new HashSet<Rating>(Arrays.asList(rating1,rating2)));
        userRepository.save(user1);

        final User user2 = new User();
        user2.setUsername("testUser");
        user2.setPassword(encoder.encode("test"));
        user2.setPrivileges(new HashSet<Privilege>(Arrays.asList(privilege1)));
        user2.setRating(new HashSet<Rating>(Arrays.asList(rating2)));
        userRepository.save(user1);
    }

    @Override
    protected void generatePrivilege() {
        Privilege privilege1 = new Privilege("MOVIE_READ_PRIVILEGE");
        privilegeRepository.save(privilege1);
        Privilege privilege2 = new Privilege("MOVIE_WRITE_PRIVILEGE");
        privilegeRepository.save(privilege2);
    }

    @Override
    protected void generateMovies() {
        Rating rating1 = new Rating("MA15+",true,"Mature Audiences Only");
        Rating rating2 = new Rating("G",false,"General Audiences");
        ratingRepository.save(rating1);
        ratingRepository.save(rating2);
    }


}
