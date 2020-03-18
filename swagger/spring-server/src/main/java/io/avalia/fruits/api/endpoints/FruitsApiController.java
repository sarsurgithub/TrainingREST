package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.ApiUtil;
import io.avalia.fruits.api.FruitsApi;
import io.avalia.fruits.entities.FruitEntity;
import io.avalia.fruits.api.model.Fruit;
import io.avalia.fruits.repositories.FruitRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class FruitsApiController implements FruitsApi {

    @Autowired
    FruitRepository fruitRepository;

    public ResponseEntity<Object> createFruit(@ApiParam(value = "", required = true) @Valid @RequestBody Fruit fruit) {
        FruitEntity newFruitEntity = toFruitEntity(fruit);
        fruitRepository.save(newFruitEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newFruitEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<Fruit>> getFruits() {
        List<Fruit> fruits = new ArrayList<>();

        for (FruitEntity fruitEntity : fruitRepository.findAll()) {
            fruits.add(toFruit(fruitEntity));
        }

        return ResponseEntity.ok(fruits);
    }
    public ResponseEntity<Void> deleteFruit(@ApiParam(value = "",required=true) @PathVariable("id") Long id) {

        fruitRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Fruit>> getFruitByAlphabeticalOrder() {
        List<Fruit> fruits = new ArrayList<>();

        for (FruitEntity fruitEntity : fruitRepository.findAllByOrderByKind()) {
            fruits.add(toFruit(fruitEntity));
        }

        return ResponseEntity.ok(fruits);

    }

    public ResponseEntity<Fruit> getFruitById(@ApiParam(value = "",required=true) @PathVariable("id") Long id) {
        FruitEntity myFruit = fruitRepository.findById(id).get();
        Fruit myFruitAsAFruit = toFruit(myFruit);
        return ResponseEntity.ok(myFruitAsAFruit);
    }

    public ResponseEntity<Object> updatefruit(@ApiParam(value = "",required=true) @PathVariable("id") Long id,
                                              @ApiParam(value = "" ,required=true )  @Valid @RequestBody Fruit fruit) {

        //get the fruit to update
        Optional<FruitEntity> fruitToUpdate = fruitRepository.findById(id);

        //create an entity from the optional
        FruitEntity fruitToUpdateAsAnEntity = fruitToUpdate.get();

        // set the fruit to update with the values of the fruit passed as an argument
        fruitToUpdateAsAnEntity.setColour(fruit.getColour());
        fruitToUpdateAsAnEntity.setKind(fruit.getKind());
        fruitToUpdateAsAnEntity.setSize(fruit.getSize());

        //return the updated fruit's location
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().build().toUri();

        //save the updated fruit
        fruitRepository.save(fruitToUpdateAsAnEntity);

        //return the message
        return ResponseEntity.created(location).build();

    }

    private FruitEntity toFruitEntity(Fruit fruit) {
        FruitEntity entity = new FruitEntity();
        entity.setColour(fruit.getColour());
        entity.setKind(fruit.getKind());
        entity.setSize(fruit.getSize());
        return entity;
    }

    private Fruit toFruit(FruitEntity entity) {
        Fruit fruit = new Fruit();
        fruit.setColour(entity.getColour());
        fruit.setKind(entity.getKind());
        fruit.setSize(entity.getSize());
        return fruit;
    }

}
