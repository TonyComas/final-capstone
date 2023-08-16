<template>
  <div class="list">
      <router-link :to="{
          name: 'user-list',
          params:{
              name: list.list_name,
              id: list.list_id
          },
      }">
        <div>
            <h1 class="name">{{list.list_name}}</h1>

        </div>
      </router-link>

  </div>

</template>

<script>
export default {
    name: "list-of-lists",
    props: ["list"],
    data() {
        return {
        }
    },

}
</script>

<style>

.name {
  background-image: linear-gradient(to right, #920ab4, rgb(11, 226, 11) 50%, white 50%);
  background-size: 200% 100%;
  background-position: -100%;
  display: inline-block;
  padding: 5px 0;
  position: relative;
  font-weight: bolder;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  transition: all 0.3s ease-in-out;
  text-decoration: none; /* Remove default underline */
}

.name:before {
  content: "";
  background: rgb(11, 226, 11);
  display: block;
  position: absolute;
  bottom: -3px;
  left: 0;
  width: 0;
  height: 1px;
  transition: all 0.2s ease-in-out;
}

.name:hover {
  background-position: 0;
}

.name:hover::before {
  width: 100%;
}

</style>