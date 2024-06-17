<script setup lang="ts">
import DRButton from '@/components/DRButton.vue'
import router from '../router'
import AppHeader from '@/components/AppHeader.vue';

const routeScrenn = (nomeRouter:String) => {
	if(nomeRouter === "us-register"){
		router.push('user-register')
	}
	else if(nomeRouter === "us-enterprise"){
		router.push('/enterprise-register')
	}
	else if(nomeRouter === "DashboardView"){
		router.push('/dashboard-view')
	}
	else if(nomeRouter === "log-view"){
		router.push('/log-view')
	}
	else{
		console.log("inserir")
	}
}
</script>
<template>
	<AppHeader>
	</AppHeader>
  <main>
	<h2>Ferramentas Adminstrativas:</h2>
	<nav class="wrapper nav">
		<DRButton button-type="home" :click-behavior="() => routeScrenn('us-register')" v-bind:disabled="false">Novo Usuário</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('us-enterprise')" v-bind:disabled="false">Nova Empresa</DRButton>
<<<<<<< HEAD
		<DRButton button-type="home" :click-behavior="() => routeScrenn('inserir')" v-bind:disabled="false">Log de Ações</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('DashboardView')" v-bind:disabled="false">Dashboards</DRButton>
=======
		<DRButton button-type="home" :click-behavior="() => routeScrenn('log-view')" v-bind:disabled="false">Log de Ações</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('inserir')" v-bind:disabled="false">Dashboards</DRButton>
>>>>>>> feat/US0011-create-user
	</nav>
  </main>
</template>
<style scoped lang="scss">
main {
	margin-top:10%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content:center;
	gap:10px;
}

.nav {
	display: flex;
	align-items: flex-end;
	justify-content: center;
	gap: 30px;
}
</style>
