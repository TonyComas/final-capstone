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
  height: 100vh;
  display: grid;
  grid-template-columns: 5vw 1fr 5vw;
  grid-template-rows: 100px 1fr 100px;
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
</style>

