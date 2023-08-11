<template>
  <div id="app">
    <PageHeader />
    <main>
      <router-view />
    </main>
    <PageFooter />
  </div>
</template>

<script>
import PageHeader from '@/components/PageHeader'
import PageFooter from '@/components/PageFooter'

export default {
  name: 'App',
  components: {
    PageHeader,
    PageFooter
  }
}
</script>

<style>

body {
  background: linear-gradient(135deg, green, black, black, green), linear-gradient(-45deg, green, black, black, green);
  background-size: 300% 300%;
  animation: gradient 15s ease-in-out infinite;
}

@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}



#app{
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 5vw 1fr 5vw;
  grid-template-rows: 120px 1fr 100px;
  grid-template-areas: ". header ."
                       ". main ."
                       ". footer .";
}

header {
  grid-area: header;
}
main {
  grid-area: main;
}
footer {
  grid-area: footer;
}
div #searchBar {
  grid-area: search;
}

</style>

