<script setup lang="ts">
import router from '@/router'

const logout = () =>{
	localStorage.clear()
	router.replace("/login")
}
</script>
<template>
  <div class="navigation">
    <img src="../assets/user-icon.png" />
  <a class="button" href="#" @click="logout">
    <div class="logout">Logout</div>
  </a>
</div>
</template>

<style scoped lang="scss">
.navigation {
  width: 100%;
}

img {
  width: 60px;
  height:65px
}

.logout {
  position: relative;
  right: -10px;
  bottom: -4px;
  overflow: hidden;
  letter-spacing: 3px;
  opacity: 0;
  transition: opacity .45s;
  -webkit-transition: opacity .35s;
  
}

.button {
	text-decoration: none;
	float: right;
  padding: 12px;
  margin: 15px;
  color: white;
  width: 25px;
  transition: width .35s;
  -webkit-transition: width .35s;
  overflow: hidden
}

a:hover {
  width: 100px;
}

a:hover .logout{
  opacity: .9;
}

a {
  text-decoration: none;
}
</style>
