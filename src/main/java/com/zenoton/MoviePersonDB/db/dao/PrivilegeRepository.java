package com.zenoton.MoviePersonDB.db.dao;

import com.zenoton.MoviePersonDB.security.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    public Privilege findByName(String name);

    @Transactional
    void removeByName(String username);

}
