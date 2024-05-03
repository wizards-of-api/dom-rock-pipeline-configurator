<script setup lang="ts">
import type { ColumnConfig } from './types'
import DRCheckBox from '../DRCheckBox.vue'
import DRTextInput from '../DRTextInput.vue'
import { onMounted } from 'vue'

type Props = {
	baseColumnConfig: ColumnConfig
}
const { baseColumnConfig } = defineProps<Props>()
const columnIndex = defineModel<string>('index')
const name = defineModel<string>('name')
const type = defineModel<string>('type')
const canBeNull = defineModel<boolean>('canBeNull')
const validColumn = defineModel<boolean>('validColumn')
const hash = defineModel<boolean>('hash')
const emit = defineEmits(['update'])
const emitUpdate = (valor:any) => {
    setTimeout(() => {
    emit('update', wrapColumnConfig())
    }, 1000)
}
onMounted(() => {
	columnIndex.value = String(baseColumnConfig.columnNumber)
	name.value = baseColumnConfig.columnName
	type.value = baseColumnConfig.type
	canBeNull.value = !!baseColumnConfig.empty
	validColumn.value = !!baseColumnConfig.valid
	hash.value = !!baseColumnConfig.hash
})
const wrapColumnConfig = () => ({
	empty: canBeNull.value ? 1: 0,
	valid: validColumn.value? 1: 0,
	hash: hash.value ?1: 0,
})
</script>
<template>
    <div
        class="grid column-config"
    >
        <DRTextInput
            style="grid-area: index"
            title="Número da Coluna"
            :default-value="String(baseColumnConfig.columnNumber)"
            v-model="columnIndex"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
        <DRTextInput
            style="grid-area: name"
            title="Nome da Coluna"
            :default-value="baseColumnConfig.columnName"
            v-model="name"
			@update="emitUpdate"
        ></DRTextInput>
        <DRTextInput
            style="grid-area: type"
            title="Tipo de Dado"
			v-model="type"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
        <div class="checkBox">
            <DRCheckBox
                style="grid-area: can-null; width: 7rem;"
                title="Pode Nulo?"
                v-model="canBeNull"
                value= "canBenull"
                @update="emitUpdate"
            ></DRCheckBox>
            <DRCheckBox
                style="grid-area: valid; width: 7rem;"
                title="Validada?"
                v-model="validColumn"
                @update="emitUpdate"
            ></DRCheckBox>
            <DRCheckBox
                style="grid-area: can-null; width: 12rem;"
                title="Usar como hash?"
                v-model="hash"
                @update="emitUpdate"
            ></DRCheckBox>
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
.column-config {
    width: 1200px;
	grid-template-columns: min-content min-content min-content min-content min-content;
	grid-template-rows: min-content 2fr;
	grid-template-areas:
		'index name type can-null  valid '
		'description description description description description';
	border-top: 1px solid var(--color-separator);
}
.checkBox{
    display: flex;
    flex-direction: row;
    gap: 20px;
}
</style>
