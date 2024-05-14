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
import ConfigurationInvalidated from './ConfigurationInvalidated.vue'
import Load from '@/components/Load.vue'
import router from '@/router'

const config = ref<BronzeConfig>()
const showLeaveModal = ref(false)
const showSavedModal = ref(false)
const hasHash = ref(false)

const getConfig = async () => {
	const response = await axios.get(`http://localhost:8080/bronze-config/${router.currentRoute.value.params.id}`)
	return response.data
}
onMounted(async () => {
	config.value = await getConfig()
})

const saveFile = async () => {
	if (!validOrInvalid() || !hasHash.value) {
		showSavedModal.value = true
		await axios.put(`http://localhost:8080/bronze-config/update/${router.currentRoute.value.params.id}`, config.value)
		showSavedModal.value = true
		return
	}
}

const validOrInvalid = () => {
	const isValidada = config.value?.columns.some(column => column.valid)
	const isHash = config.value?.columns.some(column => column.hash)
	return isValidada && isHash
}

const onCloseSavedModal = () => {
	showSavedModal.value = false
}

const goToBronzeListView = () => {
	await axios.put(`http://localhost:8080/bronze-config/update/${router.currentRoute.value.params.id}`, config.value)
	router.replace(`/list-view-bronze`)
}
</script>

<template>
	<div v-if="config">
			<div>
				<DRModal :show="showLeaveModal" @click-out="showLeaveModal = false">
						<LZModalLeave :close-modal="() => (showLeaveModal = false)"></LZModalLeave>
				</DRModal>
				<DRModal :show="showSavedModal" @close="showSavedModal = false">
						<ConfigurationInvalidated @close="onCloseSavedModal" @click-out="goToBronzeListView" :hasHash="hasHash">
						</ConfigurationInvalidated>
				</DRModal>
			</div>
			<div style="max-height: 100vh; overflow-y: scroll">
					<AppHeader> </AppHeader>
					<nav class="wrapper nav">
							<DRButton :click-behavior="() => (showLeaveModal = true)">Voltar</DRButton>
							<DRButton button-type="safe" :click-behavior="saveFile" :disabled="!hasHash">Salvar</DRButton>
					</nav>
					<main>
							<MetadataBronze :config="config"></MetadataBronze>
							<ValidHashFile :base-column-list="config?.columns" @has-hash-updated="hasHash = $event"></ValidHashFile>
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
