package web.mappers;

import org.mapstruct.Mapper;
import web.domain.Beer;
import web.model.BeerDto;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
   public BeerDto beerToBeerDto(Beer beer);
   public Beer beerDtoToBeer(BeerDto beerDto);
}
