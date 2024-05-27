<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import router from '../router'

const routeScrenn = (nomeRouter:String) => {
	switch (nomeRouter) {
	case "LZ":
		router.push('list-view')
		break
	case "BRONZE":
		router.push('list-view-bronze')
		break
	case "SILVER":
		router.push('list-view-silver')
		break
	default:
		console.error("Nome do roteador inválido:", nomeRouter)
	}
}
</script>
<template>
	<AppHeader>
	</AppHeader>
  <main>
	<h2>Selecione a etapa:</h2>
	<nav class="wrapper nav">
		<DRButton button-type="home" :click-behavior="() => routeScrenn('LZ')">Landing Zone</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('BRONZE')">Bronze</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('SILVER')">Silver</DRButton>
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
