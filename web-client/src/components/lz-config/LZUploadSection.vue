<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import DRDropDown from '../DRDropDown.vue'
import DRTextInput from '../DRTextInput.vue'
import DRButton from '../DRButton.vue'
import type { ColumnConfig } from './types'
import { VALID_COLUMN_TYPES } from './types'

const FILE_EXTENSION_TYPES = ['csv']

const emit = defineEmits(['update', 'updateColumns'])

const emitUpdate = () => {
	emit('update', wrapUpdateMetadata())
}

const fileExtension = defineModel<string>('fileExtension')
const hasHeader = defineModel<string>('hasHeader')
const fileName = defineModel<string>('fileName')
const separator = defineModel<string>('separator')

const wrapUpdateMetadata = () => ({
	fileExtension: fileExtension.value,
	hasHeader: hasHeader.value === 'Sim',
	fileName: fileName.value,
	separador: separator.value,
})

const uploadFile = () => {
	const response = [
		{
			index: 0,
			name: 'NOME',
		},
		{
			index: 1,
			name: 'VALOR',
		},
	]
	fileName.value = 'americanas.csv'

	const columnList: ColumnConfig[] = response.map((data) => Object.assign(
		{
			type: VALID_COLUMN_TYPES[0],
			canBeNull: false,
			description: '',
			status: 1,
		},
		data,
	))

	emitUpdate()
	emit('updateColumns', columnList)
}
</script>
<template>
	<div style="width: 100%">
		<DRSectionTitle title="Upload"></DRSectionTitle>
		<div class="grid upload">
			<DRDropDown
				style="grid-area: has-header; width: 7rem"
				title="Terá Header"
				:option-list="['Sim', 'Não']"
				v-model="hasHeader"
				@update="emitUpdate"
			></DRDropDown>
			<DRDropDown
				style="grid-area: type"
				title="Tipo"
				:option-list="FILE_EXTENSION_TYPES"
				v-model="fileExtension"
				@update="emitUpdate"
			></DRDropDown>
			<DRTextInput
				style="grid-area: separator"
				title="Separador"
				v-model="separator"
				size="small"
				@update="emitUpdate"
			></DRTextInput>
			<DRButton style="grid-area: upload" :click-behavior="uploadFile">Upload</DRButton>
			<DRTextInput
				style="grid-area: file-name"
				title="Nome do Arquivo"
				v-model="fileName"
				:disabled="true"
				@update="emitUpdate"
			></DRTextInput>
		</div>
	</div>
</template>

<style scoped lang="scss">
.grid {
	display: grid;
	column-gap: var(--big-gap);
	row-gap: var(--gap);
	align-items: last baseline;
	justify-content: flex-start;
	padding: var(--big-gap) 0;
}
.upload {
	grid-template-columns: min-content min-content min-content;
	grid-template-rows: min-content min-content;
	grid-template-areas:
		'has-header type separator upload'
		'file-name file-name file-name .';
}
</style>
