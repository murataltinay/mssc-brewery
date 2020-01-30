package web.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.model.v2.BeerDtoV2;
import web.service.v2.BeerServiceV2;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {
    @Autowired
    private BeerServiceV2 beerService;

    public BeerControllerV2() {
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@NotNull @PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping//POST create new beer
    public ResponseEntity handlePost(@Valid @NotNull @RequestBody BeerDtoV2 beerDto){

        BeerDtoV2 savedDto=beerService.saveNewBeer(beerDto);
        HttpHeaders headers=new HttpHeaders();
        //todo add hostname to url
        headers.add( "Location","/api/v2/beer"+savedDto.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);

    }
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDtoV2 beerDto){

        beerService.updateBeer(beerId,beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);

    }
}
