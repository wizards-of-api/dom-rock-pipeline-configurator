<script setup lang="ts">
import {  onMounted, ref } from 'vue'
import DRSectionTitle from '../DRSectionTitle.vue'
import DRDropDown from '../DRDropDown.vue'
import DRTextInput from '../DRTextInput.vue'
import DRButton from '../DRButton.vue'
import type { ColumnConfig, LZConfigView} from './types'
import { VALID_COLUMN_TYPES } from './types'
import router from '@/router'
import api from '@/JwtToken/token'

const FILE_EXTENSION_TYPES = ['csv']
type ResponseColumn = {
	column: string,
	index: number,
}

type Props = {
    valuesExistingInThisFile?: LZConfigView
}
const { valuesExistingInThisFile } = defineProps<Props>()
const emit = defineEmits(['update', 'updateColumns'])

const emitUpdate = () => {
	emit('update', wrapUpdateMetadata())
}

const fileExtension = defineModel<string>('fileExtension')
const hasHeader = defineModel<string>('hasHeader')
const fileName = defineModel<string>('fileName')
const separator = defineModel<string>('separator')
const columnListUpdate = defineModel<ColumnConfig[]>('columnList')
const inputFile = ref<HTMLInputElement>()

const wrapUpdateMetadata = () => ({
	fileExtension: fileExtension.value,
	hasHeader: hasHeader.value === 'Sim',
	fileName: fileName.value,
	separador: separator.value,
})

const uploadFile = async ($event: Event) => {
	
	const inputTarget = $event.target as HTMLInputElement
	const file = (inputTarget.files as FileList)[0]
	inputTarget.value = ''
	
	if(!file) return
	if(!separator.value) return
	if(!fileExtension.value) return
	if(!hasHeader.value) return

	fileName.value = file.name
	const formData = new FormData()
	formData.append('file', file)
	formData.append('fileName', fileName.value)
	formData.append('separator', separator.value)
	formData.append('fileExtension', fileExtension.value)
	formData.append('hasHeader', String(hasHeader.value === 'Sim'))
	try{
		const response = await api.post('/lz-config/upload-csv', formData)
		const responseColumns = response.data.columns
		const columnList: ColumnConfig[] = responseColumns.map((data: ResponseColumn) => {
		
			return Object.assign(
				{
					type: VALID_COLUMN_TYPES[0],
					canBeNull: 0,
					description: '',
					status: 1,
				},
				{
					columnName: data.column,
					columnNumber: data.index,
				},
			)
		})

		emitUpdate()
		emit('updateColumns', columnList)
	}catch(e){
		router.replace('/login')
	}
	
}

onMounted(()=>{
	fileExtension.value = valuesExistingInThisFile?.fileExtension ?? ""
	hasHeader.value = valuesExistingInThisFile?.hasHeader? "Sim": ""
	fileName.value = valuesExistingInThisFile?.fileName ?? "",
	separator.value = valuesExistingInThisFile ?"," : "",
	columnListUpdate.value =  valuesExistingInThisFile?.columns?.map((data: ColumnConfig) => {
		return Object.assign(
			{	columnId: data.columnId,
				type: data.type,
				canBeNull: data.canBeNull ? 1: 0,
				description: data.description,
				status: data.status,
			},
			{
				columnName: data.columnName,
				columnNumber: data.columnNumber,
			},
		)
	}) ?? []
	emitUpdate()
	emit('updateColumns', columnList)
})
</script>
<template>
	<div style="width: 100%" v-if="columnList || columnListUpdate">
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

			<input ref="inputFile" style="display: none;" type="file" @change="uploadFile($event)" accept=".csv"/>
			<DRButton style="grid-area: upload" :click-behavior="() => inputFile?.click()" v-bind:disabled="false">Upload</DRButton>
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
