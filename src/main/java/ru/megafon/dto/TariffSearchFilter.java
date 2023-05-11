package ru.megafon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TariffSearchFilter {
    private String name;
    private Boolean isArchive;
    private Boolean isUnlimitedVoice;
    private Boolean IsUnlimitedInternet;
}
