package ru.megafon.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import ru.megafon.dto.TariffSearchFilter;
import ru.megafon.dto.TariffServicesCategory;
import ru.megafon.entity.Tariff;
import ru.megafon.entity.TariffServices;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TariffRepository implements PanacheRepository<Tariff> {

    private static final Long UNLIMITED_SERVICES = 0L;

    @Inject
    private EntityManager entityManager;

    public Optional<Tariff> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public List<Tariff> findByFilter(TariffSearchFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tariff> query = cb.createQuery(Tariff.class);
        Root<Tariff> root = query.from(Tariff.class);
        Predicate predicate = cb.isTrue(cb.literal(true));

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            predicate = cb.and(predicate, cb.like(root.get("name"), "%" + filter.getName() + "%"));
        }
        if (filter.getIsArchive() != null) {
            predicate = cb.and(predicate, filter.getIsArchive() ? cb.isTrue(root.get("isArchived"))
                    : cb.isFalse(root.get("isArchived")));
        }
        if (filter.getIsUnlimitedVoice() != null || filter.getIsUnlimitedInternet() != null) {
            Predicate subPredicate = cb.isTrue(cb.literal(true));
            Subquery<Tariff> subquery = query.subquery(Tariff.class);
            Root<TariffServices> from = subquery.from(TariffServices.class);
            if (filter.getIsUnlimitedVoice() != null) {
                subPredicate = cb.and(subPredicate, cb.and(cb.equal(from.get("category"), TariffServicesCategory.VOICE),
                        cb.equal(from.get("value"), UNLIMITED_SERVICES)));
                subquery.select(from.get("tariff")).where(subPredicate);
                predicate = cb.and(predicate, cb.in(root).value(subquery));
            }
            if (filter.getIsUnlimitedInternet() != null) {
                subPredicate = cb.and(subPredicate, cb.and(cb.equal(from.get("category"), TariffServicesCategory.INTERNET),
                        cb.equal(from.get("value"), UNLIMITED_SERVICES)));
                subquery.select(from.get("tariff")).where(subPredicate);
                predicate = cb.and(predicate, cb.in(root).value(subquery));
            }
        }
        if (predicate != null)
            query.where(predicate);
        return entityManager.createQuery(query).getResultList();
    }

}
