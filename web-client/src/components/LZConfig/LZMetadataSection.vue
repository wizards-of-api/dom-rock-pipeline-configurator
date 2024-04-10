<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import DRDropDown from '../DRDropDown.vue'
import DRTextInput from '../DRTextInput.vue'

const emit = defineEmits(['update'])

const emitUpdate = () => {
	emit('update', wrapMetadata())
}

const name = defineModel<string>('name')
const fileOrigin = defineModel<string>('fileOrigin')
const frequencyNumber = defineModel<string>('frequencyNumber')
const frequencyType = defineModel<string>('frequencyType')

const wrapMetadata = () => ({
	name: name.value,
	fileOrigin: fileOrigin.value,
	frequencyNumber: Number(frequencyNumber.value),
	frequencyType: frequencyType.value === 'Dia' ? 'dia' : frequencyType.value === 'Mês' ? 'mes' : 'ano',
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
			v-model="fileOrigin"
			@update="emitUpdate"
		></DRTextInput>
		<DRTextInput
			style="grid-area: frequency"
			title="Frequencia"
			v-model="frequencyNumber"
			@update="emitUpdate"
		></DRTextInput>
		<DRDropDown
			style="grid-area: period"
			title="Período"
			:option-list="['Dia', 'Mês', 'Ano']"
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
	grid-template-columns: min-content min-content;
	grid-template-rows: min-content min-content;
	grid-template-areas:
		'file-name file-name'
		'config-name config-name'
		'origin origin'
		'frequency period';
}
</style>
