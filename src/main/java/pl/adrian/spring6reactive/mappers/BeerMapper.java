package pl.adrian.spring6reactive.mappers;

import org.mapstruct.Mapper;
import pl.adrian.spring6reactive.domain.Beer;
import pl.adrian.spring6reactive.model.BeerDTO;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);
}
