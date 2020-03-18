package io.avalia.fruits.api.endpoints;

import io.avalia.fruits.api.ApiUtil;
import io.avalia.fruits.api.VegetablesApi;
import io.avalia.fruits.entities.VegetableEntity;
import io.avalia.fruits.api.model.Vegetable;
import io.avalia.fruits.repositories.VegetableRepository;
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
public class VegetablesApiController implements VegetablesApi {

    @Autowired
    VegetableRepository vegetableRepository;

    public ResponseEntity<Object> createVegetable(@ApiParam(value = "", required = true) @Valid @RequestBody Vegetable vegetable) {
        VegetableEntity newVegetableEntity = toVegetableEntity(vegetable);
        vegetableRepository.save(newVegetableEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newVegetableEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    public ResponseEntity<List<Vegetable>> getVegetable() {
        List<Vegetable> vegetables = new ArrayList<>();

        for (VegetableEntity vegetableEntity : vegetableRepository.findAll()) {
            vegetables.add(toVegetable(vegetableEntity));
        }

        return ResponseEntity.ok(vegetables);
    }
    public ResponseEntity<Void> deleteVegetable(@ApiParam(value = "",required=true) @PathVariable("id") Long id) {

        vegetableRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private VegetableEntity toVegetableEntity(Vegetable vegetable) {
        VegetableEntity entity = new VegetableEntity();
        entity.setColour(vegetable.getColour());
        entity.setKind(vegetable.getKind());
        entity.setSize(vegetable.getSize());
        return entity;
    }

    private Vegetable toVegetable(VegetableEntity entity) {
        Vegetable vegetable = new Vegetable();
        vegetable.setColour(entity.getColour());
        vegetable.setKind(entity.getKind());
        vegetable.setSize(entity.getSize());
        return vegetable;
    }
}
