package com.zenoton.MoviePersonDB.db.dao;

import com.zenoton.MoviePersonDB.movie.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    public Rating findByAbbreviatedName(String name);

    @Transactional
    void removeByAbbreviatedName(String username);
}
