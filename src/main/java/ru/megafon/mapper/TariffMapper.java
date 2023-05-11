package ru.megafon.mapper;

import org.mapstruct.*;
import ru.megafon.dto.TariffDto;
import ru.megafon.entity.Tariff;

import java.util.List;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TariffServicesMapper.class})
public interface TariffMapper {
    @Named(value = "all")
    TariffDto toDto(Tariff tariff);

    @Named(value = "all")
    Tariff toEntity(TariffDto tariffDto);

    @IterableMapping(qualifiedByName = "all")
    List<TariffDto> toDtoList(List<Tariff> tariffs);

    @Mapping(target = "id", ignore = true)
    Tariff updateEntity(TariffDto tariffDto, @MappingTarget Tariff entity);

}
