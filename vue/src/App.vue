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
  background: linear-gradient(-45deg, #000000, rgba(0, 0, 0, 0.808), silver, green);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
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

