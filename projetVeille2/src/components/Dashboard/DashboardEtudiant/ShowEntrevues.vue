<template>
  <h1>Liste des entrevues</h1>
  <table>
    <tr>
      <th>Titre de l'entrevue</th>
      <th>Date de l'entrevue</th>
      <th>Heure de l'entrevue</th>
      <th>Nom de l'entreprise</th>
    </tr>
    <tr v-for="entrevue in entrevues" v-bind:key="entrevue">
      <td>{{ entrevue.titre }}</td>
      <td>{{ entrevue.date }}</td>
      <td>{{ entrevue.time }}</td>
      <td>{{ entrevue.nomEntreprise }}</td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";
import global from "../../global";
export default {
  created() {
    this.fetchUser();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const entrevues = ref({});
    return { state, fullUser, entrevues };
  },

  methods: {
    fetchUser() {
      fetch(`http://localhost:9191/user/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then(async (data) => {
          this.fullUser = data;
          await this.fetchEntrevues();
        });
    },
    fetchEntrevues() {
      fetch(`http://localhost:9191/entrevue/etudiant/${this.fullUser.id}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.entrevues = data;
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



