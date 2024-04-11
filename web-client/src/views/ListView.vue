<script setup lang="ts">
import { onMounted, ref } from 'vue'

import AppHeader from '@/components/AppHeader.vue'
import DRModal from '@/components/DRModal.vue'

import LVContainer from '@/components/list-view/LVContainer.vue'
import LVResumeModal from '@/components/list-view/LVResumeModal.vue'
type ConfigResume = {
	index: number
	name: string
}

const configList = ref<ConfigResume[]>()
const showModal = ref(false)

const getConfig = () => {
	let mockI = 1
	const mockName = 'Config '
	const mockCreate = () => {
		const config = { index: mockI, name: mockName + mockI }
		mockI++
		return config
	}
	return [...Array(20).keys()].map(() => mockCreate())
}

onMounted(() => {
	configList.value = getConfig()
})

</script>
<template>
	<div>
		<DRModal :show="showModal" @click-out="showModal = false">
			<LVResumeModal></LVResumeModal>
		</DRModal>
		<div>
			<AppHeader>
			</AppHeader>
			<LVContainer v-if="configList" :config-list="configList" :key="configList.length" :on-banner-click="() => showModal = true"></LVContainer>
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
