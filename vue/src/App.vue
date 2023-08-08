<template>
  <div id="app">
    <PageHeader />
    <main>
      <router-view />
    </main>
    <div id="searchBar"><SearchBar /></div>
    <PageFooter />
  </div>
</template>

<script>
import PageHeader from '@/components/PageHeader'
import PageFooter from '@/components/PageFooter'
import SearchBar from '@/components/SearchBar.vue'

export default {
  name: 'App',
  components: {
    PageHeader,
    PageFooter,
    SearchBar
  }
}
</script>

<style>

#app{
  background-image: linear-gradient(to bottom, rgba(67, 245, 61, 0.404), rgba(233, 114, 233, 0.753));
  display: grid;
  grid-template-columns: 5vw 1fr 20vw;
  grid-template-rows: 100px 1fr 100px;
  grid-template-areas: ". header ."
                       ". main search"
                       ". footer search";
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

