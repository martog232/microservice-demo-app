package com.example.userservice.data.repository;

import com.example.userservice.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = """
            SELECT u FROM User u WHERE u.name LIKE CONCAT('%', :nameTerm, '%')
            """)
    List<User> findAllByName(String nameTerm);

    @Query(value = """
            SELECT u FROM User u
            JOIN Country c ON c.id = u.country.id
            where c.name like CONCAT('%', :nameTerm, '%')
            OR c.code like CONCAT('%', :nameTerm, '%')
            """)
    List<User> findAllByCountryTerm(String term);
}
