package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.FruitEntity;
import io.avalia.fruits.entities.VegetableEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface VegetableRepository extends CrudRepository<VegetableEntity, Long>{

}
