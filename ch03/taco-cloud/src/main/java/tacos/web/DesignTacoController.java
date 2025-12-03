package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.Taco;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j // SLF4J 로거 생성
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        // List<Ingredient> ingredients = Arrays.asList(
        //         new Ingredient("FLTO", "흰 밀가루 또띠아", Type.WRAP),
        //         new Ingredient("COTO", "옥수수 또띠아", Type.WRAP),
        //         new Ingredient("GRBF", "갈은 소고기", Type.PROTEIN),
        //         new Ingredient("CARN", "돼지고기 속", Type.PROTEIN),
        //         new Ingredient("TMTO", "다진 토마토", Type.VEGGIES),
        //         new Ingredient("LETC", "양상추", Type.VEGGIES),
        //         new Ingredient("CHED", "체다 치즈", Type.CHEESE),
        //         new Ingredient("JACK", "몬테레이 잭 치즈", Type.CHEESE),
        //         new Ingredient("SLSA", "살사 소스", Type.SAUCE),
        //         new Ingredient("SRCR", "사워 크림", Type.SAUCE)
        // );

        // 근데 바로 리스트 안 만들고 왜 이렇게 할까?
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(item -> ingredients.add(item));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
    //    여기서 타코 디자인을 저장...
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
