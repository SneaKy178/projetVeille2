<template>
  <h1>Liste des moniteurs</h1>
  <table>
    <tr>
      <th>Nom du moniteur</th>
      <th>Courriel</th>
    </tr>
    <tr v-for="moniteur in moniteurs" v-bind:key="moniteur">
      <td>{{ moniteur.prenom }} {{ moniteur.nom }}</td>
      <td>{{ moniteur.courriel }}</td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";
import global from "../../global";
export default {
  created() {
    this.fetchmoniteurs();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const moniteurs = ref({});
    return { state, fullUser, moniteurs };
  },

  methods: {
    fetchmoniteurs() {
      fetch(`http://localhost:9191/stage/moniteurs`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.moniteurs = data;
        });
    },
  },
};
</script>
<style scoped>
table {
  margin-left: auto;
  margin-right: auto;
}
table,
td,
th {
  border: 1px solid black;
  text-align: center;
}

table {
  width: 600px;
  border-collapse: collapse;
  background-color: lightgray;
}

table th {
  background-color: black;
  color: white;
}
h1 {
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>



