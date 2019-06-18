package org.romanchi.instagram.repositories;

import org.romanchi.instagram.model.entities.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GirlsRepository extends JpaRepository<Girl, Long> {
}
