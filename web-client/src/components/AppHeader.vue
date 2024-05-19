<script setup lang="ts">
const nameRoute = (name:any) => {
  
	if(name === "HomeView"){
		return "Home"
	}else if(name === "ListView"){
		return "Configurações Landing Zone"
	}else if(name === "LZConfigView" || name === "LZConfigUpdateView"){
		return "Etapa Lading Zone"
	}else if(name === "BronzeListView"){
		return "Configurações Bronze"
	}else if(name === "BronzeConfigView"){
		return "Etapa Bronze"
	}else{
		return ""
	}
}
</script>

<template>
  <header>
    <router-link to="/home">
      <img src="../assets/dom-rock-logo.png">
    </router-link>
    <div class="user">
      <h3>{{ nameRoute($route.name) }}</h3>
      <img src="../assets/user-icon.png" height="60px"/>
    </div>
  </header>
</template>

<style lang="scss" scoped>
img {
  max-width: 154px;
}
header {
  display:flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 2rem;
  background: var(--color-background-soft);
  border-bottom: solid 1px var(--color-separator);
  min-width: 100%;
}
.user{
  display:flex;
  justify-content: space-between;
  align-items: center;
  gap: 20px;
}
</style>
