<template>
  <h1>Liste des superviseurs</h1>
  <table>
    <tr>
      <th>Nom du superviseur</th>
      <th>Courriel</th>
    </tr>
    <tr v-for="superviseur in superviseurs" v-bind:key="superviseur">
      <td>{{ superviseur.prenom }} {{ superviseur.nom }}</td>
      <td>{{ superviseur.courriel }}</td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";
import global from "../../global";
export default {
  created() {
    this.fetchSuperviseurs();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const superviseurs = ref({});
    return { state, fullUser, superviseurs };
  },

  methods: {
    fetchSuperviseurs() {
      fetch(`http://localhost:9191/stage/superviseurs`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.superviseurs = data;
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



