<script setup lang="ts">
import type { CadastroModel } from '../silver/types'
import CadastroColumnRow from '@/components/cadastro-config/CadastroColumnRow.vue'

type Props = {
	columns?: CadastroModel[]
}

const { columns } = defineProps<Props>()
const columnList = columns.map(baseColumn => baseColumn)
const emit = defineEmits(['update'])
const emitUpdate = () => {
	emit('update', columnList)
}

</script>
<template>
	<CadastroColumnRow
		v-for="columnConfig in columns"
		:column="columnConfig"
		:key="`${columnConfig.email}`"
		@update="() => {
			emitUpdate()
		}"
	>
	</CadastroColumnRow>
</template>
<style scoped lang="scss">
.hash{
	display: flex;
	flex-direction : row;
	align-items: center;
	gap:10px;
}
</style>
