<template>
    <div class="center-container">
    <div class="game-container">
        <div class="outer-circle">
    <div class="inner-circle">
          <div class="asteroids">
        <iframe id="gameFrame" :src="htmlPath" frameborder="0"></iframe>
        </div>
    </div>
    </div>
    <span></span>
    <span></span>
    <span></span>
    <span></span>
  </div>
    </div>
</template>

<script>
export default {
    data(){
        return {
            htmlPath: "/Games/Asteroids/Asteroids.html"

        };
    }

}
</script>

<style>
iframe#gameFrame{
    margin-top: 30px;
    width: 790px;
    height: 600px;
    z-index: 1;
}

.asteroids {
    text-align: center;
}

.center-container {
    display: flex;
    align-items: center;
    justify-content: center;
}


.outer-circle {
  position: relative;
  height: 640px;
  width: 800px;
  background: linear-gradient(#0bffe7, #ffeb3b, #02c412, #ff00e0);
  animation: rotate 6.5s linear infinite;
  z-index: 0;
  top: 10px;
}

.outer-circle span {
  position: absolute;
  height: 640px;
  width: 800px;
  background: linear-gradient(#0bffe7, #ffeb3b, #02c412, #ff00e0);
  z-index: 0;
}



span:nth-child(3) {
  filter: blur(25px);
}

span:nth-child(4) {
  filter: blur(150px);
}

.inner-circle {
  height: 620px;
  width: 780px;
  position: absolute;
  background: rgb(0, 0, 0);
  top: 10px;
  left: 10px;
  z-index: 2;
}

@keyframes rotate {
  0% {
    filter: hue-rotate(0deg);
  }
  100% {
    filter: hue-rotate(360deg);
  }
}
</style>