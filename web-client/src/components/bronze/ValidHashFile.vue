<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import BronzeColumnRow from './BronzeColumnRow.vue'
import type { ColumnConfig } from './types'
type Props = {
    baseColumnList?: ColumnConfig[]
}
const emit = defineEmits(['update'])
const emitUpdate = (index, data) => {
	const column = columnList[index]
	Object.assign(column, data)
	emit('update', columnList)
}
const { baseColumnList } = defineProps<Props>()
const columnList = baseColumnList?.map(baseColumn => baseColumn)
const ListHash = columnList?.filter(column => column.hash ===1).toString()
</script>
<template>
	<DRSectionTitle title="Validação e Hash"></DRSectionTitle>
		<h3>Hash:</h3>
		<p>{{ListHash}}</p>
		<BronzeColumnRow 
			v-for="(columnConfig, index) in columnList"
				:base-column-config="columnConfig"
				:key="`${index}${columnConfig}`"
				@update="(column) => { emitUpdate(index, column) }"
			>
		</BronzeColumnRow>
	<main>
	</main>
</template>
<style scoped lang="scss">
</style>
