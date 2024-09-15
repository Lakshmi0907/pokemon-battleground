package com.impact.pokemon;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * !! Feel free to change everything about this !!
 * This could be a class to hold all the Pokemon objects loaded from CSV,
 * but there are many ways to do it.
 */
@Component
public class PokemonData {
    private Map<String, Pokemon> nameToPokemon;

    PokemonData() throws IOException {
        InputStream inputStream = new ClassPathResource("data/pokemon.csv").getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        List<Pokemon> pokemons = this.loadCsvToBeans(reader);
        this.nameToPokemon = pokemons
                .stream()
                .collect(Collectors.toMap(Pokemon::getName, pokemon -> pokemon));
    }

    private List<Pokemon> loadCsvToBeans(BufferedReader reader) {
        CsvToBean<Pokemon> csvToBean = new CsvToBeanBuilder<Pokemon>(reader)
                .withType(Pokemon.class)
                .build();
        return csvToBean.parse();
    }


    public Set<String> getListOfPokemons() {
        return this.nameToPokemon.keySet();
    }

    public Pokemon getPokemonByName(String name) {
        return this.nameToPokemon.get(name);
    }
}
