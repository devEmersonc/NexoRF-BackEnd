package com.devemersonc.backend_sys_picking.DTO.productDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdateProductDTO {
    @NotBlank(message = "El código de producto es obligatorio.")
    private String sku;
    @NotBlank(message = "El nombre de producto es obligatorio.")
    private String name;
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer amount;
    @NotBlank(message = "La ubicación es obligatoria.")
    private String location;
}
