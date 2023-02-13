package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.SuperHeroes;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseJsonTests {

    ClassLoader cl = ParseJsonTests.class.getClassLoader();
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void parseJsonTest() throws Exception {
        try (InputStream resource = cl.getResourceAsStream("superheroes.json");
             InputStreamReader reader = new InputStreamReader(resource)) {
            SuperHeroes heroes = mapper.readValue(reader, SuperHeroes.class);
            assertThat(heroes.squadName).isEqualTo("Super hero squad");
            assertThat(heroes.members.get(0).name).isEqualTo("Molecule Man");
            assertThat(heroes.members.get(1).powers.get(2)).isEqualTo("Superhuman reflexes");
            assertThat(heroes.members.get(2).age).isEqualTo(1000000);
            assertThat(heroes.members.get(1).secretIdentity).isEqualTo("Jane Wilson");
        }
    }
}
