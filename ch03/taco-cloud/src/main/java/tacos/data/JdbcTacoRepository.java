package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    private JdbcTemplate jdbc;
    
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco design) {
        long tacoId = saveTacoInfo(design);
        design.setId(tacoId);
        for (Ingredient ingredient : design.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return design;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        String sql = "INSERT INTO taco (name, createdAt) VALUES (?, ?)";
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, Types.VARCHAR, Types.TIMESTAMP);
        Timestamp createdAt = new Timestamp(taco.getCreatedAt().getTime());
        String name = taco.getName();
        // 여기 Arrays.asList의 반환 타입이 뭔지 모르겠다...
        // List<String> newTaco = Arrays.asList(name, createdAt);
        PreparedStatementCreator psc = factory.newPreparedStatementCreator(Arrays.asList(name, createdAt));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update(
            "INSERT INTO Taco_Ingredient (taco, ingredient) VALUES (?, ?)",
            tacoId, ingredient.getId()
        );
    }
}
