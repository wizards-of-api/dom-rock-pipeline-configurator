<script setup lang="ts">

const nameRoute = (name:any) => {
	switch(name) {
	case "HomeView":
		return "Home"
	case "ListView":
		return "Configurações Landing Zone"
	case "LZConfigView":
	case "LZConfigUpdateView":
		return "Etapa Lading Zone"
	case "BronzeListView":
		return "Configurações Bronze"
	case "BronzeConfigView":
		return "Etapa Bronze"
	case "SilverListView":
		return "Configurações Silver"
	case "SilverConfigView":
		return "Etapa Silver"
	case "CreateEnterpriseView":
		return "Cadastro de Empresas"
	case "LogView":
		return "Tela Log"
	default:
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
