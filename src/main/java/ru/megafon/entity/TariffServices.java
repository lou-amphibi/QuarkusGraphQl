package ru.megafon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import ru.megafon.dto.TariffServicesCategory;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tariff_services")
@Where(clause = "is_removed = false")
@SQLDelete(sql = "update tariff_services set is_removed = true where id = ?")
public class TariffServices {
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

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    @NotNull
    private TariffServicesCategory category;

    @Column(name = "value", nullable = false)
    @NotNull
    private Long value;

    @Column(name = "is_removed", nullable = false)
    private Boolean isRemoved=false;

    @ManyToOne
    @JoinColumn(name = "tariff_id", foreignKey = @ForeignKey(name = "tariff_services_fk01"), nullable = false)
    private Tariff tariff;
}
