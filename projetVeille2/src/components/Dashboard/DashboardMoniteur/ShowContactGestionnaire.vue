<template>
  <h1>Contact</h1>
  <table>
    <tr>
      <th>Nom du gestionnaire</th>
      <th>Courriel</th>
    </tr>
    <tr>
      <td>{{ gestionnaire.prenom }} {{ gestionnaire.nom }}</td>
      <td>{{ gestionnaire.courriel }}</td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";
import global from "../../global";
export default {
  created() {
    this.fetchUser();
    this.fetchGestionnaire();
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const gestionnaire = ref({});
    return { state, fullUser, gestionnaire };
  },

  methods: {
    fetchUser() {
      fetch(`http://localhost:9191/user/${this.state.courriel}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.fullUser = data;
        });
    },
    fetchGestionnaire() {
      fetch(`http://localhost:9191/stage/gestionnaire/1`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.gestionnaire = data;
          console.log(this.gestionnaire, "gestionnaire");
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



