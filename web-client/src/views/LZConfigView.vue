<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import LZUploadSection from '@/components/LZConfig/LZUploadSection.vue'
import LZMetadataSection from '@/components/LZConfig/LZMetadataSection.vue'
import LZColumnSection from '@/components/LZConfig/LZColumnSection.vue'
import type { MetadataConfig, ColumnConfig } from '@/components/LZConfig/types'
import { ref } from 'vue'
import axios from 'axios'

const columnList = ref<ColumnConfig[]>([])
const columnUpdateCount = ref(0)
const updateColumnList = (newList: ColumnConfig[]) => {
	columnList.value = [...newList]
	columnUpdateCount.value += 1
}

const metadata: MetadataConfig = {
	name: '',
	fileOrigin: '',
	fileName: '',
	fileExtension: 'csv',
	frequencyNumber: 3,
	frequencyType: 'Dia',
	separator: ',',
	hasHeader: true,
}

const onUpdateMetadata = (newMetadata: MetadataConfig) => {
	Object.assign(metadata, newMetadata)
}

const onUpdateColumn = (newColumnList: ColumnConfig[]) => {
	for(let i = 0; i <= columnList.value.length; i++) columnList.value.pop()
	columnList.value.push(...newColumnList)
}

const saveFile = async () => {
	const config = {
		metadata,
		columns: columnList.value,
	}
	// const config = {
	// 	metadata,
	// 	columns: columnList.value.map((column) => {
	// 		column.value.status = 1
	// 		return column.value
	// 	}),
	// }

	console.log(config)

	const response = await axios.post('http://localhost:8080/config/save', config)
	console.log(response)
}
</script>

<template>
	<AppHeader></AppHeader>
	<main>
		<LZUploadSection @update="onUpdateMetadata" @update-columns="updateColumnList"></LZUploadSection>
		<LZMetadataSection @update="onUpdateMetadata"></LZMetadataSection>
		<LZColumnSection :base-column-list="columnList" @update="onUpdateColumn" :key="columnUpdateCount"></LZColumnSection>
		<div class="wrapper save">
			<DRButton button-type="safe" :click-behavior="saveFile">Save</DRButton>
		</div>
	</main>
</template>

<style scoped lang="scss">
.save {
	padding: var(--big-gap);
	background-color: var(--color-background);
	position: relative;
	right: 0;
	bottom: 0;
	display: flex;
	justify-content: flex-end;
	width: 100%;
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
