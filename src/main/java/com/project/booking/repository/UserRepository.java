package com.project.booking.repository;

import com.project.booking.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Dimitris Tsikos & Christos Kontosis
 */

public interface UserRepository extends JpaRepository<User, Long>{
    
    @Query("SELECT user FROM User user WHERE user.email=?1")
    Optional<User> findByEmail(String email);
        
}
