<template>
  <h1 class="center">Bonjour {{ fullUser.prenom }} {{ fullUser.nom }}</h1>
  <table>
    <tr>
      <th>Prénom nom</th>
      <th>Courriel</th>
    </tr>

    <tr v-for="etudiant in listeEtudiant" v-bind:key="etudiant">
      <td>{{ etudiant.nom }} {{ etudiant.prenom }}</td>
      <td>{{ etudiant.courriel }}</td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";
import global from "../global";
export default {
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const listeEtudiant = ref({});
    return { state, fullUser, listeEtudiant };
  },
  created() {
    this.fetchUser();
  },
  methods: {
    fetchUser() {
      fetch(`http://localhost:9191/user/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          console.log(data, "data");
          this.fullUser = data;
          console.log(this.fullUser, "fulluser");
          this.fetchListeEtudiant();
        });
    },
    fetchListeEtudiant() {
      fetch(
        `http://localhost:9191/stage/superviseur/etudiants/${this.fullUser.id}`
      )
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.listeEtudiant = data;
          console.log(this.listeEtudiant, "liste etudiant");
        });
    },
  },
};
</script>

<style scoped>
.center {
  margin-top: 50px;
  text-align: center;
}
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
</style>
