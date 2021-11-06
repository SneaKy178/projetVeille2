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
        <input type="checkbox" :checked="fullUser.hasVoiture" />
        <label>Avez-vous une voiture?</label>
      </div>

      <div>
        <input type="checkbox" :checked="fullUser.hasLicense" />
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
