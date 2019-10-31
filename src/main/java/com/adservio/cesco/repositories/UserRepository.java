package com.adservio.cesco.repositories;

import com.adservio.cesco.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
