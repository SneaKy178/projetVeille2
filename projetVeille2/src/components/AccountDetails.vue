<template>
  <form>
    <div v-if="state.isLoggedIn">
      <label>Prénom : </label>
      <input type="text" readonly :value="fullUser.prenom" />

      <label>Nom : </label>
      <input type="text" readonly :value="fullUser.nom" />

      <label>Courriel : </label>
      <input type="email" readonly :value="fullUser.courriel" />

      <label>Numéro de téléphone : </label>
      <input type="text" readonly :value="fullUser.numTelephone" />
    </div>

    <div v-if="fullUser.role === 'ETUDIANT'">
      <label>Programme : </label>
      <input type="text" readonly :value="fullUser.programme" />

      <label>Adresse : </label>
      <input type="text" readonly :value="fullUser.adresse" />

      <label>Numéro de matricule : </label>
      <input type="text" readonly :value="fullUser.numMatricule" />

      <div>
        <input type="checkbox" disabled :checked="fullUser.hasVoiture" />
        <label>Avez-vous une voiture?</label>
      </div>

      <div>
        <input type="checkbox" disabled :checked="fullUser.hasLicense" />
        <label>Avez-vous votre permis de conduite?</label>
      </div>
    </div>
    <div v-if="fullUser.role === 'SUPERVISEUR'">
      <label>Le nom de votre département : </label>
      <input type="text" readonly :value="fullUser.departement" />

      <label>Votre spécialité: </label>
      <input type="text" readonly :value="fullUser.specialite" />
    </div>
    <div v-if="fullUser.role === 'MONITEUR'">
      <label>Nom de votre entreprise : </label>
      <input type="text" readonly :value="fullUser.nomEntreprise" />

      <label>L'adresse de votre entreprise: </label>
      <input type="text" readonly :value="fullUser.adresseEntreprise" />
    </div>
    <div v-if="fullUser.role === 'GESTIONNAIRE'">
      <label>Le nom de votre département : </label>
      <input type="text" readonly :value="fullUser.departement" />
    </div>
  </form>
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
      console.log(this.state.isLoggedIn);
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
</style>