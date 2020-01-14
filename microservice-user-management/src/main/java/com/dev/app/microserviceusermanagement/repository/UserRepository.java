package com.dev.app.microserviceusermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.app.microserviceusermanagement.entity.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	@Query("SELECT u.name FROM User u WHERE u.id IN (:pIdList)")
	List<String> findByIdList(@Param(value = "pIdList") List<Long> idList);
}
