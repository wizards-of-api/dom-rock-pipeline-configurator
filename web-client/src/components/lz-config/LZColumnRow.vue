<script setup lang="ts">
import type { ColumnConfig } from './types'
import { VALID_COLUMN_TYPES } from './types'
import DRDropDown from '../DRDropDown.vue'
import DRCheckBox from '../DRCheckBox.vue'
import DRTextInput from '../DRTextInput.vue'
import DRButton from '../DRButton.vue'
import { onMounted } from 'vue'

type Props = {
	baseColumnConfig: ColumnConfig
}
const { baseColumnConfig } = defineProps<Props>()
const emit = defineEmits(['update'])
const columnId = defineModel<Number>('columnId')
const columnNumber = defineModel<string>('columnNumber')
const columnName = defineModel<string>('columnName')
const type = defineModel<string>('type')
const canBeNull = defineModel<boolean>('canBeNull')
const description = defineModel<string>('description')
const status = defineModel<string>('status', {})
const emitUpdate = () => {
	emit('update', wrapColumnConfig())
}

onMounted(() => {
	columnId.value = baseColumnConfig.columnId
	columnNumber.value = String(baseColumnConfig.columnNumber)
	columnName.value = baseColumnConfig.columnName
	type.value = baseColumnConfig.type
	canBeNull.value = Boolean(baseColumnConfig.canBeNull)
	description.value = baseColumnConfig.description
	status.value = String(baseColumnConfig.status)
})

const wrapColumnConfig = () => ({
	columnId: Number(columnId.value),
	columnNumber: Number(columnNumber.value),
	columnName: columnName.value,
	type: type.value,
	canBeNull: canBeNull.value ? 1: 0,
	description: description.value,
	status: Number(status.value),
})
</script>
<template>
    <div
        class="grid column-config"
		:key="status"
        v-if="Number(status) === 1"
    >
    <DRTextInput
        style="grid-area: index"
        title="Número da Coluna"
        :default-value="String(baseColumnConfig.columnNumber)"
        v-model="columnNumber"
        @update="emitUpdate"
        disabled
    ></DRTextInput>
        <DRTextInput
            style="grid-area: name"
            title="Nome da Coluna"
            :default-value="baseColumnConfig.columnName"
            v-model="columnName"
			@update="emitUpdate"
        ></DRTextInput>
        <DRDropDown
            style="grid-area: type"
            title="Tipo de Dado"
			v-model="type"
            :option-list="VALID_COLUMN_TYPES"
            @update="emitUpdate"
        ></DRDropDown>
        <DRCheckBox
            style="grid-area: can-null; width: 7rem;"
            title="Pode Nulo?"
			v-model="canBeNull"
            @change="emitUpdate"
            :disabled="false"
        ></DRCheckBox>
        <DRButton
            v-bind:disabled="false"
            style="grid-area: remove"
            button-type="careful"
            :click-behavior="() => {
                status = '0'
                emitUpdate()
            }"
            >Remover</DRButton
        >
        <DRTextInput
            style="grid-area: description"
            :is-text-area="true"
            :custom-height="7"
			v-model="description"
            title="Descrição"
            @update="emitUpdate"
        ></DRTextInput>
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
.column-config {
	grid-template-columns: min-content min-content min-content min-content min-content;
	grid-template-rows: min-content 2fr;
	grid-template-areas:
		'index name type can-null remove'
		'description description description description description';
	border-top: 1px solid var(--color-separator);
}
</style>
