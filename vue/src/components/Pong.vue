<template>
    <div id="pongDiv">
        <iframe id="pongGameFrame" :src="htmlPath" frameborder="0"></iframe>
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
</style>