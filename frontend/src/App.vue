<template>
  <Header v-bind="headerParams"/>
  <Footer/>
  <router-view />
</template>

<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  data() {
    return {
      headerParams: {
        variant: "10773",
        group: "P3210",
        firstName: "Ivan",
        lastName: "Androsov"
      }
    }
  }
}
</script>

<style>
@import url('http://fonts.googleapis.com/css?family=Roboto');

body{
    background-color: white;
}

#app {
  font-family: Roboto, Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* color: #2c3e50; */
}

</style>
