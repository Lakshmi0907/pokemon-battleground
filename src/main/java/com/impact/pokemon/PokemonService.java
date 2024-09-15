package com.impact.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class PokemonService {
    @Autowired
    PokemonData pokemonData;

    public Set<String> listAllPokemons() {
        return pokemonData.getListOfPokemons();
    }

    private Pokemon determineFirstToAttack(Pokemon pokemonOne, Pokemon pokemonTwo) {
        if (pokemonOne.getSpeed() >= pokemonTwo.getSpeed()) return pokemonOne;
        return pokemonTwo;
    }

    private double getEffectivenessModifier(Pokemon pokemonA, Pokemon pokemonB) {
        String pokemonAType = pokemonA.getType();
        String pokemonBType = pokemonB.getType();

        return switch (pokemonAType) {
            case "Fire" -> switch (pokemonBType) {
                case "Grass" -> 2;
                case "Water" -> 0.5;
                default -> 1;
            };
            case "Water" -> switch (pokemonBType) {
                case "Fire" -> 2;
                case "Electric" -> 0.5;
                default -> 1;
            };
            case "Grass" -> switch (pokemonBType) {
                case "Electric" -> 2;
                case "Fire" -> 0.5;
                default -> 1;
            };
            case "Electric" -> switch (pokemonBType) {
                case "Water" -> 2;
                case "Grass" -> 0.5;
                default -> 1;
            };
            default -> 1;
        };
    }

    private double calculateDamage(Pokemon attacker, Pokemon defender) {
        return
            50.0
            * ((double) attacker.getAttack() / defender.getDefense())
            * getEffectivenessModifier(attacker, defender);
    }

    private Map<String, Object> calculateBattleOutcome(Pokemon pokemonOne, Pokemon pokemonTwo) {
        Pokemon firstToAttack, lastToAttack;
        firstToAttack = determineFirstToAttack(pokemonOne, pokemonTwo);
        lastToAttack = (firstToAttack == pokemonOne) ? pokemonTwo : pokemonOne;

        int firstPokemonHitPoints = firstToAttack.getHitPoints();
        int secondPokemonHitPoints = lastToAttack.getHitPoints();

        while (firstPokemonHitPoints > 0 && secondPokemonHitPoints > 0) {
            secondPokemonHitPoints -= (int) calculateDamage(firstToAttack, lastToAttack);

            if (secondPokemonHitPoints > 0) {
                firstPokemonHitPoints -= (int) calculateDamage(lastToAttack, firstToAttack);
            }
        }

        if (firstPokemonHitPoints > 0) {
            return Map.of(
                    "winner", firstToAttack.getName(),
                    "hitPoints", firstPokemonHitPoints
            );
        } else {
            return Map.of(
                    "winner", lastToAttack.getName(),
                    "hitPoints", secondPokemonHitPoints
            );
        }
    }

    public Map<String, Object> battle(String pokemonA, String pokemonB) {
        Pokemon pokemonOne = pokemonData.getPokemonByName(pokemonA);
        Pokemon pokemonTwo = pokemonData.getPokemonByName(pokemonB);

        return calculateBattleOutcome(pokemonOne, pokemonTwo);
    }
}
