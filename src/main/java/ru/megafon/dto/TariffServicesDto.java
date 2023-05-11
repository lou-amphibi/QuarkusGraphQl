package ru.megafon.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class TariffServicesDto {

    private Long id;

    private OffsetDateTime createDate;

    private OffsetDateTime updateDate;

    @Size(min = 1, max = 128)
    @NotEmpty
    private String name;

    @NotNull
    private TariffServicesCategory category;

    @NotNull
    private Long value;
}
