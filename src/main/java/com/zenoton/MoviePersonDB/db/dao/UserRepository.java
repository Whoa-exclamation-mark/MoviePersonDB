package com.zenoton.MoviePersonDB.db.dao;

import com.zenoton.MoviePersonDB.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(final String username);

    @Transactional
    void removeUserByUsername(String username);
}
