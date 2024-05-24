<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import DRDropDown from '../DRDropDown.vue'
import DRTextInput from '../DRTextInput.vue'
import type { LZConfigView } from './types';
import { onMounted } from 'vue';

const emit = defineEmits(['update'])

const emitUpdate = () => {
	emit('update', wrapMetadata())
}
type Props = {
    valuesExistingInThisFile?: LZConfigView
}
const { valuesExistingInThisFile } = defineProps<Props>()

const name = defineModel<string>('name')
const fileOrigin = defineModel<string>('fileOrigin')
const frequencyNumber = defineModel<string>('frequencyNumber')
const frequencyType = defineModel<string>('frequencyType')

const wrapMetadata = () => ({
	name: name.value,
	fileOrigin: fileOrigin.value,
	frequencyNumber: Number(frequencyNumber.value),
	frequencyType: frequencyType.value === 'Dias' ? 'dias' : frequencyType.value === 'Meses' ? 'meses' : frequencyType.value === 'Semanas' ? 'semanas' : 'anos',
})

onMounted(()=>{
	name.value = valuesExistingInThisFile?.name ? String(valuesExistingInThisFile?.name) : ""
	fileOrigin.value = valuesExistingInThisFile?.fileOrigin ?? ""
	frequencyNumber.value = valuesExistingInThisFile?.frequencyNumber ? String(valuesExistingInThisFile?.frequencyNumber): ""
	frequencyType.value = String(valuesExistingInThisFile?.frequencyType?.toUpperCase()) ??""
})

</script>
<template>
	<DRSectionTitle title="Metadata"> </DRSectionTitle>
	<div class="grid metadata">
		<DRTextInput
			style="grid-area: config-name"
			title="Nome da Configuração"
			v-model="name"
			@update="emitUpdate"
			></DRTextInput>
		<DRTextInput
			style="grid-area: origin"
			title="Origem"
			size="small"
			v-model="fileOrigin"
			@update="emitUpdate"
		></DRTextInput>
		<DRTextInput
			style="grid-area: frequency"
			title="Frequência"
			size="small"
			v-model="frequencyNumber"
			@update="emitUpdate"
		></DRTextInput>
		<DRDropDown
			style="grid-area: period"
			title="Período"
			:option-list="['DIAS', 'MESES', 'ANOS', 'SEMANAS']"
			v-model="frequencyType"
			@update="emitUpdate"
		></DRDropDown>
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
.metadata {
	grid-template-columns: min-content min-content min-content;
	grid-template-rows: min-content min-content;
	grid-template-areas:
		'config-name config-name config-name'
		'origin frequency period'
}
</style>
