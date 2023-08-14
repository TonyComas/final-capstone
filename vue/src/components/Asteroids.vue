<template>
    <div>
        <vue-iframe id="gameFrame" :src="htmlPath"></vue-iframe>
    </div>
</template>

<script>
export default {
    data(){
        return {
            htmlPath: "/public/Games/Asteroids/Asteroids.html"

        };
    }

}
</script>

<style>

</style>