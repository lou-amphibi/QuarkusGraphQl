package ru.megafon.api;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.eclipse.microprofile.graphql.*;
import ru.megafon.dto.TariffDto;
import ru.megafon.dto.TariffSearchFilter;
import ru.megafon.service.TariffService;

import java.util.List;

@GraphQLApi
public class TariffResource {

    @Inject
    TariffService service;

    @Mutation
    @Description("add tariff")
    public TariffDto addTariff(@Valid TariffDto dto) {
        return service.addTariff(dto);
    }

    @Mutation
    @Description("update tariff")
    public TariffDto updateTariff(@Valid TariffDto dto) {
        return service.updateTariff(dto);
    }

    @Mutation
    @Description("delete tariff")
    public TariffDto deleteTariff(@Name("id") Long id) {
        return service.deleteTariff(id);
    }

    @Query("findAll")
    @Description("find all tariffs")
    public List<TariffDto> getAll() {
        return service.findAll();
    }

    @Query("findBy")
    @Description("find tariff by Id")
    public TariffDto findById(@Name("id") long id) {
        return service.findById(id);
    }

    @Query("findByName")
    @Description("find tariff by name")
    public TariffDto findByName(@Name("name") String name) {
        return service.findByName(name);
    }

    @Query("findByFilter")
    @Description("find tariff by filter")
    public List<TariffDto> findByFilter(@Name("filter") TariffSearchFilter filter) {
        return service.findByFilter(filter);
    }
}
