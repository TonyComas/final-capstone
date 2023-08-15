<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <img src="../assets/mariogaming.gif">
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
<style scoped>

.home {
  color: rgb(182, 7, 7);
}

img {
  width: 70vw;
  height: 67vh;
}

h1 {
  font-size: 16px;
}
</style>