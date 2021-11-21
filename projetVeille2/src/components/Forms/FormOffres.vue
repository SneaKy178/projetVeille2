<template>
  <div
    v-if="
      (state.isLoggedIn && state.role == 'GESTIONNAIRE') ||
      (state.isLoggedIn && state.role == 'MONITEUR')
    "
  >
    <form @submit.prevent="handleSubmit">
      <label>Titre : </label>
      <input type="text" required v-model="titre" />

      <label>Description : </label>
      <input type="text" required v-model="description" />

      <label>Nom de l'entreprise : </label>
      <input type="text" required v-model="nomEntreprise" />

      <label>Adresse de l'entreprise : </label>
      <input type="text" required v-model="adresseEntreprise" />

      <label>Date du début du stage : </label>
      <input type="date" required v-model="debutStage" />

      <label>Date de la fin du stage : </label>
      <input type="date" required v-model="finStage" />

      <label>Nombre total de semaine : </label>
      <input type="number" required v-model="totalSemaine" />

      <label>Nombre d'heure par semaine : </label>
      <input type="number" required v-model="heureSemaine" />

      <label>L'horaire de travail: </label>
      <input type="text" required v-model="horaire" />

      <label>Salaire de l'heure : </label>
      <input type="number" required v-model="salaire" />

      <div class="submit">
        <button>Créez l'offre</button>
      </div>
    </form>
  </div>
  <div v-else class="center">
    <h1>Veuillez vous conneter</h1>
    <button @click="login">Login</button>
  </div>
</template>

<script>
import global from "../global";
export default {
  setup() {
    const { state } = global;
    return { state };
  },
  data() {
    return {
      titre: "",
      description: "",
      nomEntreprise: "",
      adresseEntreprise: "",
      debutStage: "",
      finStage: "",
      totalSemaine: "",
      heureSemaine: "",
      horaire: "",
      salaire: "",
    };
  },
  methods: {
    handleSubmit() {
      //   if (!this.passwordError && !this.prenomError && !this.nomError) {
      var request = new XMLHttpRequest();
      request.open("POST", "http://localhost:9191/stage/offre");
      request.setRequestHeader(
        "Content-Type",
        "application/json; charset=UTF-8"
      );
      const offres = JSON.stringify({
        titre: this.titre,
        description: this.description,
        entreprise: this.nomEntreprise,
        adresse: this.adresseEntreprise,
        dateDebut: this.debutStage,
        dateFin: this.finStage,
        nbTotalSemaine: this.totalSemaine,
        horaire: this.horaire,
        nbTotalHeuresParSemaine: this.heureSemaine,
        tauxHoraire: this.salaire,
      });
      request.send(offres);
    },
    login() {
      this.$router.push("/login");
    },
    home() {
      this.$router.push("/home");
    },
  },
};
</script>

<style scoped>
form {
  max-width: 500px;
  margin: 30px auto;
  background: white;
  text-align: left;
  padding: 40px;
  border-radius: 10px;
}
label {
  color: #aaa;
  display: inline-block;
  margin: 25px 0 15px;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}
input,
select {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
  color: #555555;
}
input[type="checkbox"] {
  display: inline-block;
  width: 12px;
  margin: 0 10px 0 0;
  position: relative;
  top: 2px;
}
.pill {
  display: inline-block;
  margin: 20px 10px 0 0;
  padding: 6px 12px;
  background: #eee;
  border-radius: 20px;
  font-size: 12px;
  letter-spacing: 1px;
  font-weight: bold;
  color: #777;
  cursor: pointer;
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
.submit {
  text-align: center;
}

.error {
  color: #ff0062;
  margin-top: 10px;
  font-size: 0.8em;
  font-weight: bold;
}

.center {
  margin-top: 50px;
  text-align: center;
}
</style>