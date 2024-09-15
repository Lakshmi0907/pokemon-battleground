package com.impact.pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PokemonServiceTest {

    @Mock
    private PokemonData pokemonData;

    @InjectMocks
    private PokemonService pokemonService;

    private Pokemon charmander;
    private Pokemon squirtle;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mock Pokemon objects
        charmander = new Pokemon();
        charmander.setName("Charmander");
        charmander.setType("Fire");
        charmander.setSpeed(65);
        charmander.setHitPoints(39);
        charmander.setAttack(52);
        charmander.setDefense(43);

        squirtle = new Pokemon();
        squirtle.setName("Squirtle");
        squirtle.setType("Water");
        squirtle.setSpeed(43);
        squirtle.setHitPoints(44);
        squirtle.setAttack(48);
        squirtle.setDefense(65);
    }

    @Test
    public void testListAllPokemons() {
        Set<String> mockPokemons = new HashSet<>();
        mockPokemons.add("Pikachu");
        mockPokemons.add("Charizard");
        mockPokemons.add("Bulbasaur");

        when(pokemonData.getListOfPokemons()).thenReturn(mockPokemons);

        Set<String> result = pokemonService.listAllPokemons();
        assertEquals(mockPokemons, result); // Assert that the result matches the mocked data
    }

    @Test
    void testBattle_CharmanderVsSquirtle() {
        when(pokemonData.getPokemonByName("Charmander")).thenReturn(charmander);
        when(pokemonData.getPokemonByName("Squirtle")).thenReturn(squirtle);

        Map<String, Object> result = pokemonService.battle("Charmander", "Squirtle");

        assertEquals("Squirtle", result.get("winner"));
        assertEquals(24, result.get("hitPoints")); // Replace with the correct expected value
    }

    @Test
    void testBattle_SquirtleVsCharmander() {
        when(pokemonData.getPokemonByName("Squirtle")).thenReturn(squirtle);
        when(pokemonData.getPokemonByName("Charmander")).thenReturn(charmander);

        Map<String, Object> result = pokemonService.battle("Squirtle", "Charmander");

        assertEquals("Squirtle", result.get("winner"));
        assertEquals(24, result.get("hitPoints")); // Replace with the correct expected value
    }
}
