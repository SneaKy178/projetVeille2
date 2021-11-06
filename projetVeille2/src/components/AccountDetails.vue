<template>
  <p>{{ fullUser.prenom }}</p>
  <p>{{ fullUser.nom }}</p>
  <p>{{ fullUser.courriel }}</p>
  <p>{{ fullUser.password }}</p>
  <p>{{ fullUser.numTelephone }}</p>
  <div v-if="fullUser.role === 'ETUDIANT'">
    <p>{{ fullUser.programme }}</p>
    <p>{{ fullUser.adresse }}</p>
    <p>{{ fullUser.numMatricule }}</p>
    <p>{{ fullUser.hasVoiture }}</p>
    <p>{{ fullUser.hasLicense }}</p>
  </div>
  <div v-if="fullUser.role === 'SUPERVISEUR'">
    <p>{{ fullUser.departement }}</p>
    <p>{{ fullUser.specialite }}</p>
  </div>
  <div v-if="fullUser.role === 'MONITEUR'">
    <p>{{ fullUser.nomEntreprise }}</p>
    <p>{{ fullUser.adresseEntreprise }}</p>
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
      console.log(this.fullUser, "fullUser");
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
  },
};
</script>