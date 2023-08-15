<template>
    <div>
        <iframe id="gameFrame" :src="htmlPath" frameborder="0"></iframe>
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
    margin-top: 50px;
    width: 790px;
    height: 600px;
    border-width: 5px;
    border-color: green;
}
</style>