package ru.megafon;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import ru.megafon.dto.TariffServicesCategory;
import ru.megafon.entity.Tariff;
import ru.megafon.entity.TariffServices;
import ru.megafon.repository.TariffRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@ApplicationScoped
@Alternative
public class TariffTestRepository extends TariffRepository {

    @PostConstruct
    public void init() {
        Tariff tariff1 = new Tariff();
        tariff1.setName("tariff1");
        tariff1.setIsArchived(true);
        tariff1.setIsRemoved(false);

        TariffServices tariffServices1 = new TariffServices();
        tariffServices1.setName("services1");
        tariffServices1.setCategory(TariffServicesCategory.VOICE);
        tariffServices1.setValue(0l);

        tariff1.setServices(List.of(tariffServices1));
        persist(tariff1);
    }
}
