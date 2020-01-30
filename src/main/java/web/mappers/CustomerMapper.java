package web.mappers;

import org.mapstruct.Mapper;
import web.domain.Customer;
import web.model.CustomerDto;

@Mapper
public interface CustomerMapper {
    public Customer CustomerDtoToCustomer(CustomerDto customerDto);
    public CustomerDto CustomerToCustomerDto(Customer customer);
}
