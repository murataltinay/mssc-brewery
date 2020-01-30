package web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.CustomerDto;
import web.service.CustomerService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController() {
    }

    @GetMapping({"/{customerId}"})
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId){

        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }
    @PostMapping//POST create new beer
    public ResponseEntity handlePost(@Valid @RequestBody CustomerDto customerDto){

        CustomerDto savedDto=customerService.saveNewCustomer(customerDto);
        HttpHeaders headers=new HttpHeaders();
        //todo add hostname to url
        headers.add( "Location","http://localhost:8080/api/v1/customer"+savedDto.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);

    }
    @PutMapping("/{customerId}")
    public ResponseEntity handleUpdate(@PathVariable("customerId") UUID customerId,@Valid @RequestBody CustomerDto customerDto){

        customerService.updateCustomer(customerId,customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("customerId") UUID customerId){
        customerService.deleteById(customerId);

    }


}
