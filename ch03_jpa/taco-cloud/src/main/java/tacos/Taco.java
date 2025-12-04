package tacos;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
    
    @NotNull
    @Size(min = 5, message = "이름은 최소 5글자 이상이어야 합니다.")
    private String name;
    
    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "최소 1가지 재료는 골랐어야죠!")
    private List<Ingredient> ingredients;
}
