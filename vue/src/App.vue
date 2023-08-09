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

#app{
  background-image: linear-gradient(to bottom right, rgba(67, 245, 61, 0.404), rgba(233, 114, 233, 0.753));
  display: grid;
  grid-template-columns: 5vw 1fr 20vw;
  grid-template-rows: 100px 1fr 300px;
  grid-template-areas: ". header ."
                       "main main main"
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

