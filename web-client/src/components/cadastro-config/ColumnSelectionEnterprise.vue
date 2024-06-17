<script setup lang="ts">
import type { EnterpriseModel } from '../silver/types'
import EnterpriseColumnRow from './EnterpriseColumnRow.vue'

type Props = {
    columns?: EnterpriseModel[]
}

const { columns } = defineProps<Props>()
const columnList = columns.map(baseColumn => baseColumn)
const emit = defineEmits(['update'])
const emitUpdate = () => {
	emit('update', columnList)
}

</script>
<template>
	<EnterpriseColumnRow 
		v-for="columnConfig in columns"
		:column="columnConfig"
		:key="`${columnConfig.cnpj}`"
		@update="() => {
			emitUpdate()
		}"
		>
	</EnterpriseColumnRow>
</template>
<style scoped lang="scss">
.hash{
	display: flex;
	flex-direction : row;
	align-items: center;
	gap:10px;
}
</style>
