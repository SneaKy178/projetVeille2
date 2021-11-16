<template>
  <h1>Liste des etudiants</h1>
  <table>
    <tr>
      <th>Nom de l'étudiant</th>
      <th>Courriel</th>
    </tr>
    <tr v-for="etudiant in etudiants" v-bind:key="etudiant">
      <td>{{ etudiant.prenom }} {{ etudiant.nom }}</td>
      <td>{{ etudiant.courriel }}</td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";
import global from "../../global";
export default {
  created() {
    this.fetchetudiants();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const etudiants = ref({});
    return { state, fullUser, etudiants };
  },

  methods: {
    fetchetudiants() {
      fetch(`http://localhost:9191/stage/etudiants`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.etudiants = data;
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
  width: 50%;
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



