package springframework.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;
import springframework.msscbeerservice.domain.Beer;
import springframework.msscbeerservice.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
