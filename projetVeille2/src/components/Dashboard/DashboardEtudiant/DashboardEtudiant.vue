<template>
  <div v-if="state.isLoggedIn" class="center">
    <h1>Bonjour {{ fullUser.prenom }} {{ fullUser.nom }}</h1>
    <ShowCv />
    <ShowContantSuperviseur />
    <ShowOffres />
    <ShowEntrevues />
  </div>
  <div v-else class="center">
    <h1>Veuillez vous conneter</h1>
    <button @click="login">Login</button><br />
  </div>
</template>

<script>
import ShowCv from "./ShowCv.vue";
import ShowContantSuperviseur from "./ShowContactSuperviseur.vue";
import ShowOffres from "./ShowOffres.vue";
import ShowEntrevues from "./ShowEntrevues.vue";
import { ref } from "vue";
import global from "../../global";
export default {
  components: {
    ShowCv,
    ShowContantSuperviseur,
    ShowOffres,
    ShowEntrevues,
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
.center {
  margin-top: 50px;
  text-align: center;
  width: 400px;
  left: 50%;
  position: relative;
  transform: translate(-50%);
}

button {
  background: #0b6dff;
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  font-size: 16px;
}
</style>
