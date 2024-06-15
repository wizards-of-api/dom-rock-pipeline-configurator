<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import router from '../router'
import { onMounted } from 'vue'
const permission = defineModel<number>('permission')

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
onMounted(() =>{
	permission.value = Number(localStorage.getItem('permission'))
	console.log(permission.value)

})
</script>
<template>
	<AppHeader>
	</AppHeader>
  <main>
	<h2>Selecione a etapa:</h2>
	<nav class="wrapper nav">
		<DRButton button-type="home" :click-behavior="() => routeScrenn('LZ')" :disabled="permission === 6 || permission === 7 || permission === 8 ?true:false">Landing Zone</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('BRONZE')" :disabled="permission === 2 || permission === 8 ?true:false">Bronze</DRButton>
		<DRButton button-type="home" :click-behavior="() => routeScrenn('SILVER')" :disabled="permission === 2 || permission === 3 || permission === 6 ?true:false">Silver</DRButton>
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
