<script setup lang="ts">
import { onMounted, ref } from 'vue'
import type { ColumnConfig } from './types'
import DRSectionTitle from '@/components/DRSectionTitle.vue'
import DRDropDown from '@/components/DRDropDown.vue'
import DRButton from '@/components/DRButton.vue'
import LZColumnRow from './LZColumnRow.vue'

type Props = {
    baseColumnList: ColumnConfig[]
}

const filterInactive = (column: ColumnConfig) => column.status === 0
const mapOptions = (column: ColumnConfig) => `${column.index} ${column.name}`

const { baseColumnList } = defineProps<Props>()
const columnList = baseColumnList.map(baseColumn => baseColumn)
const activeColumnCount = ref(0)

const emit = defineEmits(['update'])
const emitUpdate = () => {
	emit('update', columnList)
}

const setActiveCount = () => activeColumnCount.value = columnList.filter(filterInactive).length

const toAdd = defineModel('toAdd', {
	get: (value: any) => {
		if(value === undefined) return value
		return Number(value.split(' ')[0])
	},
})
const addColumn = () => {
	if(!toAdd.value || toAdd.value === '') return
	const columnIndex = toAdd.value as number
	columnList[columnIndex].status = 1
	setActiveCount()
}

const columnUpdate = (index: number, column: ColumnConfig) => {
	columnList[index] = column
	setActiveCount()
}

onMounted(() => {
	setActiveCount()
})
</script>
<template>
    <div
        class="wrapper container"
    >
        <DRSectionTitle style="grid-area: title" title="Configurar Colunas"></DRSectionTitle>
        <div class="wrapper flex add-column" :key="activeColumnCount">
            <DRDropDown
                title="Adicionar Nova Coluna"
                :option-list="columnList.filter(filterInactive).map(mapOptions)"
				v-model="toAdd"
				@update="emitUpdate"
            >
            </DRDropDown>
            <DRButton
                :click-behavior="addColumn"
            > Adicionar
			</DRButton>
        </div>
		<LZColumnRow 
			v-for="(columnConfig, index) in columnList"
			:base-column-config="columnConfig"
			@update="(newValue: ColumnConfig) => {
				columnUpdate(index, newValue)
				emitUpdate()
			}"
			:key="`${index}${columnConfig.status}`"
		>
		</LZColumnRow>
    </div>

</template>
<style scoped lang="scss">
.wrapper.flex {
	display: flex;
	padding: var(--big-gap) 0;
	align-items: last baseline;
	gap: var(--big-gap);
}
</style>
