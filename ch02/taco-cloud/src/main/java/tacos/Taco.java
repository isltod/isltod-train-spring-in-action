package tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min = 5, message = "이름은 최소 5글자 이상이어야 합니다.")
    private String name;
    @Size(min = 1, message = "최소 1가지 재료는 골랐어야죠!")
    private List<String> ingredients;
}
