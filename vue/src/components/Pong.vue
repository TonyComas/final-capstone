<template>
    <div id="pongDiv">
        <div class="center-container">
    <div class="game-container">
        <div class="box">
  <div class="box__line box__line--top"></div>
  <div class="box__line box__line--right"></div>
  <div class="box__line box__line--bottom"></div>
  <div class="box__line box__line--left"></div>
        <iframe id="pongGameFrame" :src="htmlPath" frameborder="0"></iframe>
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
    margin-top: 20px;
    width: 680px;
    height: 430px;
    border-width: 5px;
    border-color: green;
}
div#pongDiv {
    text-align: center;
}
/* 
:root {
  --line-width: 8px;
  --line-color: #d808b5;
}

.center-container {
    display: flex;
    align-items: center;
    justify-content: center;
}

.box__line {
  position: absolute;
  background: var(--line-color);
}

.box__line--top,
.box__line--bottom {
  height: var(--line-width);
  width: 50%;
}

.box__line--top::before,
.box__line--top::after,
.box__line--bottom::before,
.box__line--bottom::after {
  content: "";
  position: absolute;
  width: var(--line-width);
  height: var(--line-width);
  background: var(--line-color);
}

.box__line--top {
  top: calc(-1 * var(--line-width));
}

.box__line--top::before {
  left: 0;
  bottom: calc(-1 * var(--line-width));
}

.box__line--top::after {
  right: 0;
  bottom: calc(-1 * var(--line-width));
}

.box__line--bottom {
  bottom: calc(-1 * var(--line-width));
}

.box__line--bottom::before {
  left: 0;
  top: calc(-1 * var(--line-width));
}

.box__line--bottom::after {
  right: 0;
  top: calc(-1 * var(--line-width));
}

.box__line--left,
.box__line--right {
  width: var(--line-width);
  height: 100%;
}

.box__line--left {
  left: calc(-1 * var(--line-width));
}

.box__line--right {
  right: calc(-1 * var(--line-width));
} */

</style>