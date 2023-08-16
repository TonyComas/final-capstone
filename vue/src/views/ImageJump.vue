<template>
  <div @keydown.space="jump" tabindex="0">
    <img
      :src="imageUrl"
      alt="Jumping Image"
      :style="{ transform: `translateY(${jumpHeight}px)` }"
    />
  </div>
</template>

<script>
export default {
    data() {
    return {
      imageUrl: '../assets/luigi-talk.gif',
      jumpHeight: 50,
    };
  },
  methods: {
    jump() {
      
      this.jumpHeight = -40; 
      setTimeout(() => {
        this.jumpHeight = 0; 
      }, 300);
    },
  },
};
</script>

<style>

</style>