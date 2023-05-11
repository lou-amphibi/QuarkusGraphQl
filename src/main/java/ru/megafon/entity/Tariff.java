package ru.megafon.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tariff")
@Where(clause = "is_removed = false")
@SQLDelete(sql = "update tariff set is_removed = true where id = ?")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false, nullable = false)
    private OffsetDateTime createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private OffsetDateTime updateDate;

    @Size(min = 1, max = 128)
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;

    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved = false;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tariff", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<TariffServices> services = new ArrayList<>();

    public void setServices(List<TariffServices> services) {
        this.services.clear();
        services.forEach(it -> it.setTariff(this));
        this.getServices().addAll(services);
    }

}
