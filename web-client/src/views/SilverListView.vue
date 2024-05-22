<script setup lang="ts">
import { onMounted, ref } from 'vue'
import AppHeader from '@/components/AppHeader.vue'
import DRModal from '@/components/DRModal.vue'
import LVSilverContainer from '@/components/list-view/LV-silver/LVSilverContainer.vue'
import type { LZConfig } from '@/components/lz-config/types'
import axios from 'axios'

const configList = ref<LZConfig[]>([])
const showModal = ref(false)
const selectedConfig = ref<LZConfig | null>(null)

const getConfig = async () => {
	const response = await axios.get('http://localhost:8080/lz-config/list-view')
	console.log(response)
	return response.data.content
}

onMounted(async () => {
	
	configList.value = await getConfig()
})
</script>

<template>
	<div>
		<DRModal :show="showModal" @click-out="showModal = false">
			<LVSilverContainer
				v-if="selectedConfig"
				:key="selectedConfig?.fileId"
				:lz-config="selectedConfig"
			></LVSilverContainer>
		</DRModal>
		<div>
			<AppHeader></AppHeader>
			<LVSilverContainer
				v-if="configList.length"
				:config-list="configList"
				:key="configList.length"
			></LVSilverContainer>
		</div>
	</div>
	<main></main>
</template>

<style scoped lang="scss">
main {
	flex-grow: 1;
}
</style>
