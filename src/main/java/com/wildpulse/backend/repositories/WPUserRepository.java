package com.wildpulse.backend.repositories;

import com.wildpulse.backend.models.WPUser;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WPUserRepository extends CrudRepository<WPUser, Long> {
    Optional<WPUser> findByEmail(String email);
}
