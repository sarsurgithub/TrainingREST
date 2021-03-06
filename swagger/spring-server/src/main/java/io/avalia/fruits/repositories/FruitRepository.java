package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.FruitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface FruitRepository extends CrudRepository<FruitEntity, Long>{
    List<FruitEntity> findAllByOrderByKind();
}
