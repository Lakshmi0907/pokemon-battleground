package com.impact.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.*;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokemonControllerTest {

    private final TestRestTemplate rest;

    @MockBean
    private PokemonService pokemonService;

    PokemonControllerTest(@LocalServerPort int port) {
        rest = new TestRestTemplate(new RestTemplateBuilder().rootUri(format("http://localhost:%d", port)));
    }

    @Test
    void testAttackPicksWinnerWithHitPoints() {
        String pokemonA = "Pikachu";
        String pokemonB = "Charizard";

        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("winner", "Pikachu");
        expectedResult.put("hitPoints", 120);

        when(pokemonService.battle(pokemonA, pokemonB)).thenReturn(expectedResult);

        Map response = rest.getForObject("/attack?pokemonA={pokemonA}&pokemonB={pokemonB}", Map.class, pokemonA, pokemonB);

        assertEquals(2, response.size());
        assertEquals("Pikachu", response.get("winner"));
        assertEquals(120, response.get("hitPoints"));
    }

    @Test
    public void testListPokemons() {
        Set<String> mockPokemons = new HashSet<>();
        mockPokemons.add("Pikachu");
        mockPokemons.add("Charmander");

        when(pokemonService.listAllPokemons()).thenReturn(mockPokemons);

        Set response = rest.getForObject("/list-pokemons", Set.class);

        assertArrayEquals(mockPokemons.toArray(), response.toArray());
    }
}
