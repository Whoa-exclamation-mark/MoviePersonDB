package com.zenoton.MoviePersonDB.db.data;

import com.zenoton.MoviePersonDB.db.dao.PrivilegeRepository;
import com.zenoton.MoviePersonDB.db.dao.RatingRepository;
import com.zenoton.MoviePersonDB.db.dao.UserRepository;
import com.zenoton.MoviePersonDB.movie.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public abstract class DataGenerator implements ApplicationRunner{
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PrivilegeRepository privilegeRepository;

    @Autowired
    public RatingRepository ratingRepository;

    @Autowired
    public PasswordEncoder encoder;


    public DataGenerator(){
        //generateData();
    }

    protected abstract void generateUsers();
    protected abstract void generatePrivilege();
    protected abstract void generateMovies();
    public void generateData(){
        generatePrivilege();
        generateMovies();
        generateUsers();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        generateData();
    }
}
