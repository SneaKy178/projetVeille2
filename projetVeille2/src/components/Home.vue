<template>
  <div v-if="state.isLoggedIn" class="center">
    <div>
      <h1>Bienvu {{ fullUser.prenom }} {{ fullUser.nom }}</h1>
    </div>
    <button @click="accountDetails">Voir information du compte</button><br />
    <div
      v-if="fullUser.role === 'MONITEUR' || fullUser.role === 'GESTIONNAIRE'"
    >
      <button @click="createOffer">Créer une offre de stage</button>
    </div>
  </div>
  <div v-else class="center">
    <h1>Veuillez vous conneter</h1>
    <button @click="login">Login</button><br />
  </div>
</template>

<script>
import { ref } from "vue";
import global from "./global";
export default {
  setup() {
    const { state } = global;
    const fullUser = ref({});
    return { state, fullUser };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      console.log(this.state.courriel);
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
    accountDetails() {
      this.$router.push("/accountDetails");
    },
    createOffer() {
      this.$router.push("/formOffres");
    },
    login() {
      this.$router.push("/login");
    },
  },
};
</script>

<style>
.center {
  margin-top: 50px;
  text-align: center;
}
</style>