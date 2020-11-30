package pl.mr.controller.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Data
public class ChildSearch {
//    @NotEmpty
    @NotNull
    @Size(min = 11, max = 11,message = "Pesel musi mieć 11 znaków")
    private String pesel;
}
