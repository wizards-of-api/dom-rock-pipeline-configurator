<script setup lang="ts">
import { onMounted, ref } from 'vue'
import AppHeader from '@/components/AppHeader.vue'
import DRModal from '@/components/DRModal.vue'
import router from '@/router'
import LVContainer from '@/components/list-view/LV-lz/LVContainer.vue'
import LVResumeModal from '@/components/list-view/LV-lz/LVResumeModal.vue'
import api from '@/JwtToken/token'
import type { LZConfig } from '@/components/lz-config/types'

const configList = ref<LZConfig[]>()
const showModal = ref(false)
const selectedConfig = ref<LZConfig>()

const getConfig = async () => {
	try {
		const response = await api.get('/lz-config/list-view')
		return response.data.content
	} catch (error) {
		router.replace("/login")
	}
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
