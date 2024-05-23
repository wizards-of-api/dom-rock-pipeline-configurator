<script setup lang="ts">
import { onMounted, ref } from 'vue'
import AppHeader from '@/components/AppHeader.vue'
import LVSilverContainer from '@/components/list-view/LV-silver/LVSilverContainer.vue'
import type { validatedFileBronze } from '@/components/lz-config/types'
import axios from 'axios'

const configList = ref<validatedFileBronze>()
const getConfig = async () => {
	const response = await axios.get('http://localhost:8080/silver/get-bronze-validated')
	return response.data
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
