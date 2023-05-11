package ru.megafon.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import ru.megafon.entity.TariffServices;

@ApplicationScoped
public class TariffServicesRepository implements PanacheRepository<TariffServices> {
}
