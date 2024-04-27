<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'

import AppHeader from '@/components/AppHeader.vue'
import DRModal from '@/components/DRModal.vue'

import LVBronzeContainer from '@/components/list-view/LV-bronze/LVBronzeContainer.vue'
import LVBronzeResumeModal from '@/components/list-view/LV-bronze/LVBronzeResumeModal.vue'

import type { LZConfig } from '@/components/lz-config/types'

const configList = ref<LZConfig[]>()
const showModal = ref(false)
const selectedConfig = ref<LZConfig>()

const getConfig = async () => {
	const response = await axios.get('http://localhost:8080/list-view')
	console.log(response)
	return response.data.content
}

onMounted(async () => {
	
	configList.value = await getConfig()
})

const onBannerClick = (config: LZConfig) => {
	selectedConfig.value = config
	showModal.value = true
}

</script>
<template>
	<div>
		<DRModal :show="showModal" @click-out="showModal = false">
			<LVBronzeResumeModal v-if="selectedConfig" :key="selectedConfig?.fileId" :lz-config="selectedConfig"></LVBronzeResumeModal>
		</DRModal>
		<div>
			<AppHeader>
			</AppHeader>
			<LVBronzeContainer v-if="configList" :config-list="configList" :key="configList.length" :on-banner-click="onBannerClick"></LVBronzeContainer>
		</div>
	</div>
  <main>

  </main>
</template>
<style scoped lang="scss">
main {
  flex-grow: 1;
}
</style>
