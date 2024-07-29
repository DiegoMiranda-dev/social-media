package com.sm.socialmedia.repository;

import com.sm.socialmedia.user.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolUsuario, Long> {

}
