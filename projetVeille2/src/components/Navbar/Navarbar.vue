<template>
  <nav>
    <div class="menu-item">
      <div v-if="state.isLoggedIn">
        <router-link to="/accountDetails" class="link"
          >Account Details</router-link
        >
        <router-link
          to="/dashboardEtudiant"
          class="link"
          v-if="state.role === 'ETUDIANT'"
          >Dashboard</router-link
        >
        <router-link
          to="/dashboardMoniteur"
          class="link"
          v-if="state.role === 'MONITEUR'"
          >Dashboard</router-link
        >
        <router-link
          to="/dashboardSuperviseur"
          class="link"
          v-if="state.role === 'SUPERVISEUR'"
          >Dashboard</router-link
        >
        <router-link
          to="/dashboardGestionnaire"
          class="link"
          v-if="state.role === 'GESTIONNAIRE'"
          >Dashboard</router-link
        >
        <router-link to="/dndCv" class="link" v-if="state.role === 'ETUDIANT'"
          >Cv</router-link
        >

        <router-link
          to="/formOffres"
          class="link"
          v-if="state.role === 'MONITEUR' || state.role === 'GESTIONNAIRE'"
          >Offres</router-link
        >
      </div>
      <div v-if="!state.isLoggedIn">
        <router-link to="/formEtudiant" class="link">Form Etudiant</router-link>

        <router-link to="/formMoniteur" class="link">Form Moniteur</router-link>
        <router-link to="/formSuperviseur" class="link"
          >Form Superviseur</router-link
        >
        <router-link to="/accountDetails" class="link"
          >Account Details</router-link
        >
        <router-link to="/login" class="link">Login</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
import global from "../global";
export default {
  setup() {
    const { state } = global;
    return { state };
  },
};
</script>

<style>
nav {
  display: flex;
  align-items: center;
  justify-content: center;
}
nav .menu-item {
  color: #fff;
  padding: 10px 20px;
  position: relative;
  text-align: center;
  border-bottom: 3px solid transparent;
  display: flex;
  transition: 0.4s;
}

nav .menu-item a {
  color: inherit;
  text-decoration: none;
}
.link {
  margin-left: 10px;
  margin-right: 10px;
}
</style>

