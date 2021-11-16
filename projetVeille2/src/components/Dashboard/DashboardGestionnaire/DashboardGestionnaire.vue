<template>
  <div v-if="state.isLoggedIn" class="center">
    <h1>Bonjour {{ fullUser.prenom }} {{ fullUser.nom }}</h1>
    <ShowOffres />
    <ShowEtudiants />
    <ShowMoniteurs />
    <ShowSuperviseurs />
    <ShowCvs />
  </div>
  <div v-else class="center">
    <h1>Veuillez vous conneter</h1>
    <button @click="login">Login</button><br />
  </div>
</template>

<script>
import ShowOffres from "./ShowOffres.vue";
import ShowEtudiants from "./ShowEtudiants.vue";
import ShowMoniteurs from "./ShowMoniteurs.vue";
import ShowSuperviseurs from "./ShowSuperviseurs.vue";
import ShowCvs from "./ShowCvs.vue";
import { ref } from "vue";
import global from "../../global";
export default {
  components: {
    ShowOffres,
    ShowEtudiants,
    ShowMoniteurs,
    ShowSuperviseurs,
    ShowCvs,
  },
  setup() {
    const { state } = global;
    const fullUser = ref({});
    return { state, fullUser };
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
        });
    },
    login() {
      this.$router.push("/login");
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
  margin-top: 50px;
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
