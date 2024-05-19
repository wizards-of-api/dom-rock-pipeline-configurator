<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'

import type { MetadataConfig, ColumnConfig, LZConfigView} from '@/components/lz-config/types'

import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import DRModal from '@/components/DRModal.vue'

import LZUploadSection from '@/components/lz-config/LZUploadSection.vue'
import LZMetadataSection from '@/components/lz-config/LZMetadataSection.vue'
import LZColumnSection from '@/components/lz-config/LZColumnSection.vue'
import LZModalLeave from '@/components/lz-config/LZModalLeave.vue'
import LZModalSaved from '@/components/lz-config/LZModalSaved.vue'
import router from '@/router'

const config = ref<LZConfigView>()
let columnList: ColumnConfig[] = config.value?.columns ?? []
const columnUpdateCount = ref(0)
const updateColumnList = (newList: ColumnConfig[]) => {
	onUpdateColumn(newList)
	columnUpdateCount.value += 1
}

const metadata: MetadataConfig = {
	name: config.value?.name,
	fileOrigin:config.value?.fileOrigin,
	fileName: config.value?.fileName,	
	fileExtension: config.value?.fileExtension,
	frequencyNumber: config.value?.frequencyNumber,
	frequencyType: config.value?.frequencyType,
	separator: ',',
	hasHeader: Boolean(config.value?.hasHeader),
}

const showLeaveModal = ref(false)
const showSavedModal = ref(false)

const onUpdateMetadata = (newMetadata: MetadataConfig) => {
	Object.assign(metadata, newMetadata)
}

const onUpdateColumn = (newColumnList: ColumnConfig[]) => {
	columnList = newColumnList
}

const getConfig = async () => {
	const response = await axios.get(`http://localhost:8080/lz-config/${router.currentRoute.value.params.id}`)
	return response.data
}
onMounted(async () => {
	config.value = await getConfig()
})

const saveFile = async () => {
	await axios.put(`http://localhost:8080/lz-config/update/${router.currentRoute.value.params.id}`, config.value)
	router.replace(`/list-view-bronze`)
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
				<DRButton :click-behavior="() => showLeaveModal = true">Voltar</DRButton>
				<DRButton button-type="safe" :click-behavior="saveFile">Salvar</DRButton>
			</nav>
			<main>
				<LZUploadSection v-if="config" @update="onUpdateMetadata" :values-existing-in-this-file="config" @update-columns="updateColumnList"></LZUploadSection>
				<LZMetadataSection v-if="config" @update="onUpdateMetadata" :values-existing-in-this-file="config"></LZMetadataSection>
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
