<template>
  <nav>
    <div class="menu-item"> <router-link to="/">Home</router-link></div>
    <Dropdown title="Form" :items="services"/>
  </nav>
</template>

<script>
import Dropdown from './Dropdown.vue'
export default {
  name: "navbar",
  components: {Dropdown},
  data() {
    return {
      services: [
        {
          title: "Signup",
          link: "/signupForm",
        },
        {
          title: "Design",
          link: "#",
        },
        {
            title: "Videos",
            link: '#',
        }
      ],
    };
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

