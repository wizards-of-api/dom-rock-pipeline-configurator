<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import router from '../router'

const routeScrenn = (nomeRouter:String) => {
	if(nomeRouter === "LZ"){
		router.push('list-view')
	}
	else if(nomeRouter === "BRONZE"){
		console.log("BRONZE")
	}else{
		console.log("Silver")
	}
}
</script>
<template>
	<AppHeader>
	</AppHeader>
  <main>
	<h1>Selecione a etapa:</h1>
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
