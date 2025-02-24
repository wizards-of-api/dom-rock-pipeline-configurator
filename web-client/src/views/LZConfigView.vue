<script setup lang="ts">
import { ref } from 'vue'

import type { MetadataConfig, ColumnConfig } from '@/components/lz-config/types'

import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import DRModal from '@/components/DRModal.vue'

import LZUploadSection from '@/components/lz-config/LZUploadSection.vue'
import LZMetadataSection from '@/components/lz-config/LZMetadataSection.vue'
import LZColumnSection from '@/components/lz-config/LZColumnSection.vue'
import LZModalLeave from '@/components/lz-config/LZModalLeave.vue'
import LZModalSaved from '@/components/lz-config/LZModalSaved.vue'
import api from '@/JwtToken/token'
import router from '@/router'


let columnList: ColumnConfig[] = []
const columnUpdateCount = ref(0)
const updateColumnList = (newList: ColumnConfig[]) => {
	onUpdateColumn(newList)
	columnUpdateCount.value += 1
}

const metadata: MetadataConfig = {
	name: '',
	fileOrigin: '',
	fileName: '',
	fileExtension: 'csv',
	frequencyNumber: 3,
	frequencyType: 'Dias',
	hasHeader: true,
}

const showLeaveModal = ref(false)
const showSavedModal = ref(false)

const onUpdateMetadata = (newMetadata: MetadataConfig) => {
	Object.assign(metadata, newMetadata)
}

const onUpdateColumn = (newColumnList: ColumnConfig[]) => {
	columnList = newColumnList
}

const saveFile = async () => {
	const cnpj = localStorage.getItem('cnpj')
	const config = {
		metadata,
		columns: columnList,
	}
	try {
		await api.post(`/lz-config/save/${cnpj}`, config)
		showSavedModal.value = true
	} catch (error) {
		router.replace("/login")
	}
}
</script>

<template>
	<div>
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
				<DRButton :click-behavior="() => showLeaveModal = true" v-bind:disabled="false">Voltar</DRButton>
				<DRButton button-type="safe" :click-behavior="saveFile" v-bind:disabled="false">Salvar</DRButton>
			</nav>
			<main>
				<LZUploadSection @update="onUpdateMetadata" @update-columns="updateColumnList"></LZUploadSection>
				<LZMetadataSection @update="onUpdateMetadata"></LZMetadataSection>
				<LZColumnSection :base-column-list="columnList" @update="onUpdateColumn" :key="columnUpdateCount"></LZColumnSection>
			</main>
		</div>
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
	position: relative;
	padding: var(--big-gap);
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}
</style>
