package com.impact.pokemon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PokemonDataTest {
    private PokemonData pokemonData;

    @BeforeEach
    void setUp() throws IOException {
        pokemonData = new PokemonData();
    }

    @Test
    void testGetListOfPokemons() {
        Set<String> pokemons = pokemonData.getListOfPokemons();

        assertNotNull(pokemons);
        assertEquals(pokemons.size(), 487);
    }

    @Test
    void testGetPokemonByName() {
        Pokemon pokemon = pokemonData.getPokemonByName("Bulbasaur");
        assertNotNull(pokemon);
    }
}
