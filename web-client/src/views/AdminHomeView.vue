<script setup lang="ts">
import AdminAppHeader from '@/components/AdminAppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import router from '../router'

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
	else{
		console.log("inserir")
	}
}
</script>
<template>
	<AdminAppHeader>
	</AdminAppHeader>
  <main>
	<h2>Ferramentas Adminstrativas:</h2>
	<nav class="wrapper nav">
		<DRButton button-type="home" :click-behavior="() => routeScrenn('us-register')" v-bind:disabled="false">Novo Usuário</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('us-enterprise')" v-bind:disabled="false">Nova Empresa</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('inserir')" v-bind:disabled="false">Log de Ações</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('DashboardView')" v-bind:disabled="false">Dashboards</DRButton>
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
