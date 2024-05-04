<script setup lang="ts">
import { onBeforeMount} from 'vue'
import DRSectionTitle from '../DRSectionTitle.vue'
import BronzeColumnRow from './BronzeColumnRow.vue'
import type { ColumnConfig } from './types'
type Props = {
    baseColumnList?: ColumnConfig[] | []
}
const { baseColumnList } = defineProps<Props>()
const columnList = baseColumnList?.map(baseColumn => baseColumn)
const emit = defineEmits(['update'])
const nameHash = defineModel<string>('nameHash')
const listHashValue =(columnList:ColumnConfig[]) =>{
	const ListHash = columnList?.filter(column => column.hash === 1)
	const valueHash = ListHash?.map(e => e.columnName.replace(""," "))
	nameHash.value = valueHash.toString()
}
const emitUpdate = (index:any, data:any) => {
	if(columnList){
		const column = columnList[index] 
	    Object.assign(column, data)
		emit('update', columnList, listHashValue(columnList))
	}
}

onBeforeMount(()=> {
	listHashValue(columnList??[])
})
</script>
<template>
	<DRSectionTitle title="Validação e Hash"></DRSectionTitle>
		<div class=hash>
			<h3>Hash:</h3>
			<h3>{{nameHash}}</h3>
		</div>
		
			<BronzeColumnRow 
				v-for="(columnConfig, index) in columnList"
					:base-column-config="columnConfig"
					:key="`${index}${columnConfig}`"
					@update="(column) => {emitUpdate(index, column)}"
				>
			</BronzeColumnRow>
	<main>
	</main>
</template>
<style scoped lang="scss">
.hash{
	display: flex;
	flex-direction : row;
	align-items: center;
	gap:10px;
}
</style>
