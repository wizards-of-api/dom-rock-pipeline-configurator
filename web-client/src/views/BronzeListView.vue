<script setup lang="ts">
import { onMounted, ref } from 'vue'
import AppHeader from '@/components/AppHeader.vue'
import DRModal from '@/components/DRModal.vue'
import LVBronzeContainer from '@/components/list-view/LV-bronze/LVBronzeContainer.vue'
import LVBronzeResumeModal from '@/components/list-view/LV-bronze/LVBronzeResumeModal.vue'
import type { LZConfig } from '@/components/lz-config/types'
import api from '@/JwtToken/token'
import router from '@/router'

const configList = ref<LZConfig[]>()
const showModal = ref(false)
const selectedConfig = ref<LZConfig>()

const getConfig = async () => {
	try {
		const cnpj = localStorage.getItem('cnpj')
		const response = await api.get(`/lz-config/list-view/company/${cnpj}`)
		return response.data
	} catch (error) {
		router.replace("/login")
	}
}

onMounted(async () => {
	try {
		configList.value = await getConfig()
	} catch (error) {
		router.replace("/login")
	}
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
			<LVBronzeContainer v-if= "configList" :config-list="configList" :key="configList.length" :on-banner-click="onBannerClick"></LVBronzeContainer>
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
