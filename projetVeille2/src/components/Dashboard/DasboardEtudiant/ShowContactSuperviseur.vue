<template>
  <h1>Contact</h1>
  <table>
    <tr>
      <th>Nom du superviseur</th>
      <th>Courriel</th>
    </tr>
    <tr>
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
    this.fetchUser();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const superviseur = ref({});
    return { state, fullUser, superviseur };
  },

  methods: {
    fetchUser() {
      fetch(`http://localhost:9191/user/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.fullUser = data;
          this.superviseur = data.superviseur;
          console.log(this.superviseur, "superviseur");
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



