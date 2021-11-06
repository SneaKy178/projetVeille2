<template>
  <nav>
    <!-- <div v-if="!fullUser.isLoggedIn === 'true'"> -->
    <div class="menu-item"><router-link to="/home">Home</router-link></div>
    <div class="menu-item">
      <router-link to="/formEtudiant">Form Etudiant</router-link>
    </div>
    <div class="menu-item">
      <router-link to="/formMoniteur">Form Moniteur</router-link>
    </div>
    <div class="menu-item">
      <router-link to="/formSuperviseur">Form Superviseur</router-link>
    </div>
    <div class="menu-item">
      <router-link to="/accountDetails">Account Details</router-link>
    </div>
    <!-- </div> -->
    <div class="menu-item">
      <router-link to="/login">Login</router-link>
    </div>
  </nav>
</template>

<script>
export default {
  name: "navbar",
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

nav .menu-item.active,
nav .menu-item:hover {
  background-color: #444;
  border-bottom-color: cyan;
}

nav .menu-item a {
  color: inherit;
  text-decoration: none;
}
</style>

