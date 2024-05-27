<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import SilverColumnRow from './SilverColumnRow.vue'
import type {ColumnConfig, SilverConfig} from './types'


type Props = {
    baseColumnList: SilverConfig[]
}

const { baseColumnList } = defineProps<Props>()
const columnList = baseColumnList.map(baseColumn => baseColumn)
const emit = defineEmits(['update'])
const emitUpdate = () => {
	emit('update', columnList)
}

</script>
<template>
	<DRSectionTitle title="De/Para"></DRSectionTitle>
	<SilverColumnRow 
		v-for="columnConfig in baseColumnList"
		:base-column-config="columnConfig"
		:key="`${columnConfig.silverId}`"
		@update="(newValue: ColumnConfig) => {
			emitUpdate()
		}"
		>
	</SilverColumnRow>
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
