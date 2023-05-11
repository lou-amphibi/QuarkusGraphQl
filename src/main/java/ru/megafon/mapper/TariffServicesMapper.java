package ru.megafon.mapper;

import org.mapstruct.*;
import ru.megafon.dto.TariffDto;
import ru.megafon.dto.TariffServicesDto;
import ru.megafon.entity.Tariff;
import ru.megafon.entity.TariffServices;

import java.util.List;

@Mapper(componentModel = "jakarta", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TariffServicesMapper {
    @Named(value = "all")
    TariffServicesDto toDto(TariffServices entity);

    @Named(value = "all")
    TariffServices toEntity(TariffServicesDto dto);

    @IterableMapping(qualifiedByName = "all")
    List<TariffServicesDto> toDtoList(List<TariffServices> entity);

    @IterableMapping(qualifiedByName = "all")
    List<TariffServices> toEntityList(List<TariffServicesDto> dto);
}
