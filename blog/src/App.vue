<template>
  <nav>
    <router-link to="/personal/Home">我的</router-link>
    <span>-----------</span>
    <router-link to="/blog/Home">博客</router-link>
  </nav>
  <router-view/>
</template>

<script>

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

body{
  margin:0;
  padding: 0;
  border: 0;
}



</style>
