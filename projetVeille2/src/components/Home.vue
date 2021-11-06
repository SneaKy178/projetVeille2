<template>
  <div class="center">
    <h1>Bienvu {{ fullUser.prenom }} {{ fullUser.nom }}</h1>
    <button @click="test">Go to account details</button>
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
    test() {
      this.$router.push("/accountDetails");
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