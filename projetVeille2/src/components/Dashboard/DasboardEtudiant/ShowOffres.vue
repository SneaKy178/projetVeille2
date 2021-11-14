<template>
  <h1>Liste des offres</h1>
  <table>
    <tr>
      <th>Titre de l'offre</th>
      <th>Description de l'offre</th>
    </tr>
    <tr v-for="offre in offres" v-bind:key="offre">
      <td>{{ offre.titre }}</td>
      <td>{{ offre.description }}</td>
    </tr>
  </table>
</template>

<script>
import { ref } from "vue";
import global from "../../global";
export default {
  created() {
    this.fetchUser();
    this.fetchoffres();
    setInterval(() => this.fetchoffres(), 100);
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    const offres = ref({});
    return { state, fullUser, offres };
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
    fetchoffres() {
      fetch(`http://localhost:9191/stage/offres/etudiant/${this.fullUser.id}`)
        .then((res) => {
          return res.json();
        })
        .then((data) => {
          this.offres = data;
        });
    },
    getFile() {
      this.files = this.$refs.myFiles.files[0];
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



