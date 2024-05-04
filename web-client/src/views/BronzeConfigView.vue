<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'
import { type BronzeConfig } from '@/components/bronze/types'
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import DRModal from '@/components/DRModal.vue'
import MetadataBronze from '@/components/bronze/MetadataBronze.vue'
import ValidHashFile from '@/components/bronze/ValidHashFile.vue'
import LZModalLeave from '@/components/lz-config/LZModalLeave.vue'
import ConfigurationInvalidated from '@/views/ConfigurationInvalidated.vue'
import Load from '@/components/Load.vue'

const config = ref<BronzeConfig>()
const showLeaveModal = ref(false)
const showSavedModal = ref(false)
const getConfig = async () => {
	const response = await axios.get('http://localhost:8080/bronze-config/1')
	return response.data
}
onMounted(async () => {
	config.value = await getConfig()
})
const saveFile = async () => {
	await axios.put(`http://localhost:8080/bronze-config/update/1`, config.value)
	console.log(config.value)
	showSavedModal.value = true
}

const onCloseSavedModal = () => {
	showSavedModal.value = false
}

const goToBronzeListView = () => {
	
}

</script>

<template>
	<div v-if="config">
		<div>
			<DRModal :show="showLeaveModal" @click-out="showLeaveModal = false">
				<LZModalLeave :close-modal="() => (showLeaveModal = false)"></LZModalLeave>
			</DRModal>
			<DRModal :show="showSavedModal" @close="showSavedModal = false">
				<ConfigurationInvalidated @close="onCloseSavedModal" @click-out="goToBronzeListView"></ConfigurationInvalidated>
			</DRModal>
		</div>
		<div style="max-height: 100vh; overflow-y: scroll">
			<AppHeader> </AppHeader>
			<nav class="wrapper nav">
				<DRButton :click-behavior="() => (showLeaveModal = true)">Voltar</DRButton>
				<DRButton button-type="safe" :click-behavior="saveFile">Salvar</DRButton>
			</nav>
			<main>
				<MetadataBronze :config="config"></MetadataBronze>
				<ValidHashFile :base-column-list="config.columns"></ValidHashFile>
			</main>
		</div>
	</div>
	<div v-if="!config">
		<Load></Load>
	</div>
</template>

<style scoped lang="scss">
.nav {
	padding: var(--gap) var(--big-gap);
	background: var(--color-background-mute);
	position: sticky;
	top: 0;
	z-index: 10;

	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 100%;
	border-bottom: 1px solid var(--color-separator);
}

main {
	margin: 0 auto;
	border: 1px solid var(--color-separator);
	border-top: 0;
	border-bottom: 0;
	width: 100%;
	max-width: 1280px;
	padding: var(--big-gap);
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	gap: 30px;
}
</style>
