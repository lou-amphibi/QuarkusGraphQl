package ru.megafon.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class TariffDto {

    private Long id;

    private OffsetDateTime createDate;

    private OffsetDateTime updateDate;

    @Size(min = 1, max = 128)
    @NotEmpty
    private String name;

    @NotNull
    private Boolean isArchived;

    private List<TariffServicesDto> services;
}
