<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import type { ColumnConfig} from './types'
import DRDropDown from '../DRDropDown.vue'
import DRTextInput from '../DRTextInput.vue'
import DRButton from '../DRButton.vue'
import axios from 'axios'
import type { LZConfigView } from '../lz-config/types'
import { onMounted } from 'vue'

type Props = {
	fileConfig?: LZConfigView
}
const { fileConfig } = defineProps<Props>()
const emit = defineEmits(['update'])
const emitUpdate = () => {
	emit('update', wrapColumnConfig())
}
const columnList = fileConfig?.columns?.map(columns => columns)
const filterActive = (column: ColumnConfig) => column.status === 1
const mapOptions = (column: ColumnConfig) => `${column.columnNumber} ${column.columnName}`

const fromC = defineModel<string>('fromC')
const toC = defineModel<string>('toC')
const columnId = defineModel<number>('columnId')
const saveFile = async () => {
	console.log(fileConfig)
	const toFromJson = {
		columnId:columnId.value,
	 	from:fromC.value,
	 	to:toC.value,
	}
	console.log(toFromJson)
	await axios.post(`http://localhost:8080/silver-config/save`, toFromJson)}



const toAdd = defineModel('toAdd', {
	get: (value: any) => {
		const filteredColumnNumber: number | undefined = parseInt(value?.split(' ')[0])
		const filteredColumn = columnList?.filter(column => column.columnNumber === filteredColumnNumber)
		console.log(filteredColumn?.[0])
		columnId.value = Number(filteredColumn?.[0]?.columnId)
		return value
	},
})

const wrapColumnConfig = () => ({
	columnId: toAdd,
	from:fromC.value,
	to:toC.value,
})

</script>
<template>
	<div>
		<div class="wrapper">
			<h2>{{ fileConfig?.name }}</h2>
			<hr/>
    	</div>
		<main>
			<div class="containerMetadata">
				<div class="titleInfo">
						<DRDropDown 
						title="Coluna"
                		:option-list="columnList?.filter(filterActive).map(mapOptions)"
						v-model="toAdd"
						@update="emitUpdate"
							></DRDropDown>
						<div class="buttonAlign">
							<DRButton 
							:click-behavior="saveFile">Adcionar
							</DRButton>
							</div>
						<div class="rowMetadata">
							<div class="textInfo">
							<DRTextInput
							style="grid-area: index"
							title="De"
							v-model="fromC"
							@update="emitUpdate"
							></DRTextInput>
							</div>
							<div class="textInfo">
							<DRTextInput
							style="grid-area: index"
							title="Para"
							@update="emitUpdate"
							v-model="toC"
							></DRTextInput>
							</div>
						</div>
					</div>	
				</div>
		</main>
	</div>
</template>
<style scoped lang="scss">
main{
	width:100vh;
	margin-top:10px;
}
.containerMetadata{
	display:flex;
	flex-direction: row;
	justify-content: space-between;
}
.buttonAlign{
	display:flex;
	width: 400%;
	height: 50px;
    flex-direction: row;
    justify-content: end;
}
.rowMetadata{
	display:grid;
	grid-template-columns: 1fr 1fr 100px 1fr;
	grid-template-rows:1fr;
	align-items: center;
	column-gap: 25%;
    flex-direction: column;
    justify-content: space-between;
}
.titleInfo{
	display:flex;
	width: 30%;
	flex-direction: column;
	gap:10px;
}
.sizeWord{
	font-size:20px;
}
.textInfo{
	display:flex;
	flex-direction: row;
	gap:10px;
}

.wrapper {
    width: 100%;
}
h2 {
    padding: var(--small-gap) 0;
}
hr {
    border: 0;
    border-top: 2px solid var(--color-separator);
}

</style>
