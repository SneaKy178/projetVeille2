<template>
  <h1>Liste des cvs</h1>
  <table>
    <tr>
      <td>Le nombre total de cvs</td>
      <td>{{ cvs.length }}</td>
    </tr>
    <tr>
      <th>Nom du cv</th>
      <th>Nom de l'étudiant</th>
    </tr>
    <tr v-for="cv in cvs" v-bind:key="cv">
      <td>{{ cv.nom }}</td>
      <td>{{ cv.etudiant.prenom }}</td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";
import global from "../../global";
export default {
  created() {
    this.fetchCvs();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const cvs = ref({});

    return { state, fullUser, cvs };
  },

  methods: {
    fetchCvs() {
      fetch(`http://localhost:9191/stage/getCVs`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.cvs = data;
          console.log(data, "cvs");
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
td {
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



