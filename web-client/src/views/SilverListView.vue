<script setup lang="ts">
import { onMounted, ref } from 'vue'
import AppHeader from '@/components/AppHeader.vue'
import LVSilverContainer from '@/components/list-view/LV-silver/LVSilverContainer.vue'
import type { validatedFileBronze } from '@/components/lz-config/types'
import router from '@/router'
import api from '@/JwtToken/token'

const configList = ref<validatedFileBronze>()
const getConfig = async () => {
	try{
		const cnpj = localStorage.getItem('cnpj')
		const response = await api.get(`/silver/get-bronze-validated/${cnpj}`)
		return response.data
	}catch(e){
		router.replace(`/login`)
	}
}
onMounted(async () => {
	configList.value = await getConfig()
})
</script>
<template>
	<div v-if="configList">
		<AppHeader></AppHeader>
		<LVSilverContainer
			v-if="configList?.files"
			:config-list="configList.files"
			:key="configList.files.length"
		></LVSilverContainer>
	</div>
	<main></main>
</template>
<style scoped lang="scss">
main {
	flex-grow: 1;
}
</style>
