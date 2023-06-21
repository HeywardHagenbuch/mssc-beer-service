package springframework.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springframework.msscbeerservice.repositories.BeerRepository;
import springframework.msscbeerservice.web.controller.NotFoundException;
import springframework.msscbeerservice.web.mappers.BeerMapper;
import springframework.msscbeerservice.web.model.BeerDto;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(
                beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeerById(UUID beerId, BeerDto beerDto) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).map(beer -> {
                    beer.setBeerName(beerDto.getBeerName());
                    beer.setBeerStyle(beerDto.getBeerStyle().name());
                    beer.setPrice(beerDto.getPrice());
                    beer.setUpc(beerDto.getUpc());
                    return beerRepository.save(beer);
                }).orElseThrow(NotFoundException::new));
    }
}
