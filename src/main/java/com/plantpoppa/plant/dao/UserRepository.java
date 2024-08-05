package com.plantpoppa.plant.dao;

import com.plantpoppa.plant.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public List<UserEntity> findAll();

    public Optional<UserEntity> findByUserId(int userId);
}
