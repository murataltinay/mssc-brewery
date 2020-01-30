package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.BeerDto;
import web.service.BeerService;

import javax.validation.Valid;
import java.util.UUID;

@Deprecated
@RequestMapping("/api/v1/beer")
@RestController
//@CrossOrigin
public class BeerController {

    @Autowired
    private BeerService beerService;

    public BeerController() {
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }
    @PostMapping//POST create new beer
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto){
        //beerDto.setId(UUID.randomUUID());
        BeerDto savedDto=beerService.saveNewBeer(beerDto);
        HttpHeaders headers=new HttpHeaders();
        //todo add hostname to url
        headers.add( "Location","/api/v1/beer/"+savedDto.getId().toString());
        //Gson
        //String response="{\"status\":\"ok\"}";
        //return beerDto;
        return new ResponseEntity(headers,HttpStatus.CREATED);

    }
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto){

        beerService.updateBeer(beerId,beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);

    }
}