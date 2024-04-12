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
const emitUpdate = () => {
	emit('update', wrapColumnConfig())
}

const columnIndex = defineModel<string>('index')
const name = defineModel<string>('name')
const type = defineModel<string>('type')
const canBeNull = defineModel<boolean>('canBeNull')
const description = defineModel<string>('description')
const status = defineModel<string>('status', {})

onMounted(() => {
	columnIndex.value = String(baseColumnConfig.index)
	name.value = baseColumnConfig.name
	type.value = baseColumnConfig.type
	canBeNull.value = baseColumnConfig.canBeNull
	description.value = baseColumnConfig.description
	status.value = String(baseColumnConfig.status)
})

const wrapColumnConfig = () => ({
	index: Number(columnIndex.value),
	name: name.value,
	type: type.value,
	canBeNull: canBeNull.value,
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
            style="grid-area: name"
            title="Nome da Coluna"
            :default-value="baseColumnConfig.name"
            v-model="name"
			@update="emitUpdate"
        ></DRTextInput>
        <DRTextInput
            style="grid-area: index"
            title="Número da Coluna"
            :default-value="String(baseColumnConfig.index)"
            v-model="columnIndex"
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
            @update="emitUpdate"
        ></DRCheckBox>
        <DRButton
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
