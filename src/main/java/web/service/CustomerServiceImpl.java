package web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import web.model.BeerDto;
import web.model.CustomerDto;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID beerId) {
        return CustomerDto.builder().id(UUID.randomUUID()).customerName("Murat").build() ;
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        //todo impl - would add a real impl to update customer
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("Deleting...");
    }
}
