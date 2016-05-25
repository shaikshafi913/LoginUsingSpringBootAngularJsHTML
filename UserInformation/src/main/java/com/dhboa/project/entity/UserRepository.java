package com.dhboa.project.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




/**
 * @author DarkHorse
 *
 */
@Repository

public interface UserRepository extends JpaRepository <User, String> {



	
}
