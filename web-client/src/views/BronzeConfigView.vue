<script setup lang="ts">
import { onMounted, ref } from 'vue'
import {  type BronzeConfig } from '@/components/bronze/types'
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import DRModal from '@/components/DRModal.vue'
import MetadataBronze from '@/components/bronze/MetadataBronze.vue'
import ValidHashFile from '@/components/bronze/ValidHashFile.vue'
import LZModalLeave from '@/components/lz-config/LZModalLeave.vue'
import LZModalSaved from '@/components/lz-config/LZModalSaved.vue'
import Load from '@/components/Load.vue'
import router from '@/router'
import api from '@/JwtToken/token'

const config = ref<BronzeConfig>()
const showLeaveModal = ref(false)
const showSavedModal = ref(false)
const getConfig = async () => {
	try {
		const response = await api.get(`/bronze-config/${router.currentRoute.value.params.id}`)
		return response.data
	} catch (error) {
		router.replace("/login")
	}
}
onMounted(async () => {
	config.value = await getConfig()
})

const saveFile = async () => {
	try {
		await api.put(`/bronze-config/update/${router.currentRoute.value.params.id}`, config.value)
		router.replace(`/list-view-bronze`)
	} catch (error) {
		router.replace("/login")
	}
	
}
</script>
<template>
	<div v-if="config">
		<div>
			<DRModal :show="showLeaveModal" @click-out="showLeaveModal = false">
				<LZModalLeave :close-modal="() => showLeaveModal = false"></LZModalLeave>
			</DRModal>
			<DRModal :show="showSavedModal">
				<LZModalSaved></LZModalSaved>
			</DRModal>
		</div>
		<div style="max-height: 100vh; overflow-y: scroll;">
			<AppHeader>
			</AppHeader>
			<nav class="wrapper nav">
				<DRButton :click-behavior="() => showLeaveModal = true">Voltar</DRButton>
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
	position:sticky;
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
	gap:30px;
}
</style>
