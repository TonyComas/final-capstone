<template>
    <div id="pongDiv">
        <div class="pong-center-container">
    <div class="pong-game-container">
        <div class="outer-circle">
            <div class="inner-circle">
        <iframe id="pongGameFrame" :src="htmlPath" frameborder="0"></iframe>
        </div>
        </div>
        </div>
        </div>
    </div>
    
</template>

<script>
export default {
    data(){
        return {
            htmlPath: "/Games/Pong/index.html"

        };
    }

}
</script>

<style>
iframe#pongGameFrame{
    margin-top: 30px;
    width: 790px;
    height: 600px;
    z-index: 1;
}
div#pongDiv {
    text-align: center;
}

.pong-center-container {
    display: flex;
    align-items: center;
    justify-content: center;
}

.pong-outer-circle {
  position: relative;
  height: 640px;
  width: 800px;
  background: linear-gradient(#0bffe7, #ffeb3b, #02c412, #ff00e0);
  animation: rotate 6.5s linear infinite;
  z-index: 0;
  top: 10px;
}

.pong-outer-circle span {
  position: absolute;
  height: 640px;
  width: 800px;
  background: linear-gradient(#14ffe9, #ffeb3b, #02c412, #ff00e0);
  z-index: 0;
}

span:nth-child(3) {
  filter: blur(25px);
}

span:nth-child(4) {
  filter: blur(150px);
}

.pong-inner-circle {
  height: 620px;
  width: 780px;
  position: absolute;
  background: black;
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