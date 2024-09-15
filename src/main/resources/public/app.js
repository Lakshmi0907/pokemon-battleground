const app = Vue.createApp({
    setup() {
        let pokemonName = Vue.ref('________');
        let pokemons = Vue.ref([]);
        let selectedLeft = Vue.ref('');
        let selectedRight = Vue.ref('');
        
        function battle () {
            fetch(`/attack?pokemonA=${selectedLeft.value}&pokemonB=${selectedRight.value}`)
                .then(response => response.json())
                .then(data => {
                    pokemonName.value = data.winner;
                    console.log(`This pokemon has ${data.hitPoints} hit points.`);
                })
        }

        function fetchPokemons() {
            fetch("/list-pokemons")
                .then(response => response.json())
                .then(data => {
                    pokemons.value = data;
                })
                .catch(error => {
                    alert("Error while fetching data");
                })
        }

        Vue.onMounted(fetchPokemons);

        return {
            pokemonName,
            pokemons,
            selectedLeft,
            selectedRight,
            battle,
        }
    }
});
app.mount('#app');
