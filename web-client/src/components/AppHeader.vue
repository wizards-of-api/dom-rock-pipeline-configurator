<script setup lang="ts">
import { onMounted, ref } from 'vue'
import type { RouteLocationRaw } from 'vue-router'
import LogOut from './LogOut.vue'

const nameRoute = (name:any) => {
	switch(name) {
	case "HomeView":
		return "Home"
	case "ListView":
		return "Configurações Landing Zone"
	case "LZConfigView":
	case "LZConfigUpdateView":
		return "Etapa Landing Zone"
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
		return "Tabela de Logs"
	case "UserRegister":
		return "Cadastro de Usuarios"
	case "Administrador":
		return "Administrador"
	case "DashboardView":
		return "DashBoard"
	default:
		return ""
	}
}
const nomeRoute = ref<RouteLocationRaw>('/home')
onMounted(()=> {
	const user = localStorage.getItem("permission")
	if(user ==="1"){
		nomeRoute.value = "/admin-home"
	}else{
		nomeRoute.value =  "/home"
	}
})
</script>

<template>
  <header>
    <router-link :to="nomeRoute">
      <img src="../assets/dom-rock-logo.png">
    </router-link>
	<div class="name_img">
		<h3>{{ nameRoute($route.name) }}</h3>
		<div class="user">
		<LogOut></LogOut>
		</div>
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
  gap: 23px;
}

.name_img{
	display: flex;
	flex-direction: row;
	align-items: center;
}
</style>
