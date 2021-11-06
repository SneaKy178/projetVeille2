<template>
  <h1>Bienvu sur mon site</h1>
  <p>{{ state.courriel }}</p>
  <p>{{ state.password }}</p>
</template>

<script>
import global from "./global";
export default {
  setup() {
    const { state } = global;
    return { state };
  },
};
</script>

<style>
</style>