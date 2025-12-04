package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				repository.save(new Ingredient("FLTO", "흰 밀가루 또띠아", Type.WRAP));
                repository.save(new Ingredient("COTO", "옥수수 또띠아", Type.WRAP));
                repository.save(new Ingredient("GRBF", "갈은 소고기", Type.PROTEIN));
                repository.save(new Ingredient("CARN", "돼지고기 속", Type.PROTEIN));
                repository.save(new Ingredient("TMTO", "다진 토마토", Type.VEGGIES));
                repository.save(new Ingredient("LETC", "양상추", Type.VEGGIES));
                repository.save(new Ingredient("CHED", "체다 치즈", Type.CHEESE));
                repository.save(new Ingredient("JACK", "몬테레이 잭 치즈", Type.CHEESE));
                repository.save(new Ingredient("SLSA", "살사 소스", Type.SAUCE));
                repository.save(new Ingredient("SRCR", "사워 크림", Type.SAUCE));
			}
			
		};
	}
}
