<template>
    <div id="tetrisDiv">
        <div class="tetris-center-container">
    <div class="tetris-game-container">
        <div class="tetris-outer-circle">
            <div class="tetris-inner-circle">
        <iframe id="tetrisGameFrame" :src="htmlPath" frameborder="0"></iframe>
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
            htmlPath: "/Games/Tetris/index.html"

        };
    }

}
</script>

<style>
iframe#tetrisGameFrame{
    margin-left: -20px;
    margin-top: -5px;
    width: 670px;
    height: 710px;
    z-index: 1;
}
div#tetrisDiv {
    text-align: center;
}

.tetris-center-container {
    display: flex;
    align-items: center;
    justify-content: center;
}

.tetris-outer-circle {
  position: relative;
  height: 650px;
  width: 650px;
  background: linear-gradient(#14ffe9, #ffeb3b, #ff00e0);
  animation: rotate 6.5s linear infinite;
  z-index: 0;
  top: 10px;
}

.tetris-outer-circle span {
  position: absolute;
  height: 650px;
  width: 650px;
  background: linear-gradient(#14ffe9, #ffeb3b, #ff00e0);
  z-index: 0;
}

span:nth-child(3) {
  filter: blur(25px);
}

span:nth-child(4) {
  filter: blur(150px);
}

.tetris-inner-circle {
  height: 630px;
  width: 630px;
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