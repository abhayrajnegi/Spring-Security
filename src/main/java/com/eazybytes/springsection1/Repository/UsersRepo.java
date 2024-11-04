package com.eazybytes.springsection1.Repository;


import com.eazybytes.springsection1.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, String> {

    @Query(value = "select * from users", nativeQuery = true)
    public List<Users> findAllByNativeQuery();

    public List<Users>findByUsername(String username);

}
