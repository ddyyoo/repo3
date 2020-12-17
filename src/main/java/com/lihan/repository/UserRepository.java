package com.lihan.repository;

import com.lihan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    public List<User> findAll();
}
