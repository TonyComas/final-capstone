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

</style>