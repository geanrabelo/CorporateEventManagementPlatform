package com.br.CEMP.repository;

import com.br.CEMP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
