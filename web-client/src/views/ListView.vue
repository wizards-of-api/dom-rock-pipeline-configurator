<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'

import AppHeader from '@/components/AppHeader.vue'
import DRModal from '@/components/DRModal.vue'

import LVContainer from '@/components/list-view/LVContainer.vue'
import LVResumeModal from '@/components/list-view/LVResumeModal.vue'

import type { LZConfig } from '@/components/lz-config/types'

const configList = ref<LZConfig[]>()
const showModal = ref(false)
const selectedConfig = ref<LZConfig>()

const getConfig = async () => {
	const response = await axios.get('http://localhost:8080/lz-config/list-view')
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
			<LVResumeModal v-if="selectedConfig" :key="selectedConfig?.fileId" :lz-config="selectedConfig"></LVResumeModal>
		</DRModal>
		<div>
			<AppHeader>
			</AppHeader>
			<LVContainer v-if="configList" :config-list="configList" :key="configList.length" :on-banner-click="onBannerClick"></LVContainer>
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
