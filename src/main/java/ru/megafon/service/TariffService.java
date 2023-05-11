package ru.megafon.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.val;
import org.hibernate.PersistentObjectException;
import ru.megafon.dto.TariffDto;
import ru.megafon.dto.TariffSearchFilter;
import ru.megafon.entity.Tariff;
import ru.megafon.mapper.TariffMapper;
import ru.megafon.repository.TariffRepository;

import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class TariffService {
    @Inject
    private TariffRepository repository;

    @Inject
    private TariffMapper mapper;

    @Transactional
    public TariffDto addTariff(TariffDto tariffDto) {
        val entity = mapper.toEntity(tariffDto);
        repository.persistAndFlush(entity);
        val out  = repository.findByName(entity.getName()).orElseThrow(NotFoundException::new);
        return mapper.toDto(out);
    }

    @Transactional
    public TariffDto updateTariff(TariffDto tariffDto) {
        val entity = repository.findByName(tariffDto.getName()).orElseThrow(NotFoundException::new);
        mapper.updateEntity(tariffDto, entity);
        entity.getServices().forEach(it -> it.setTariff(entity));
        repository.persistAndFlush(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public TariffDto deleteTariff(Long id) {
        val entity = repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
        if (repository.deleteById(id)) {
            return mapper.toDto(entity);
        }
        throw new PersistentObjectException("Error while tey to delete tariff with id: " + id);
    }

    public List<TariffDto> findAll() {
        List<Tariff> out = repository.findAll().stream().collect(Collectors.toList());
        return mapper.toDtoList(out);
    }

    public TariffDto findByName(String name) {
        val entity = repository.findByName(name).orElseThrow(NotFoundException::new);
        return mapper.toDto(entity);
    }

    public TariffDto findById(Long id) {
        val entity = repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
        return mapper.toDto(entity);
    }

    public List<TariffDto> findByFilter(TariffSearchFilter filter) {
        val out = repository.findByFilter(filter);
        return mapper.toDtoList(out);
    }
}
