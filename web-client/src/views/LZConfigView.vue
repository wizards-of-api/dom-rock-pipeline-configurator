<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import DRSectionTitle from '@/components/DRSectionTitle.vue'
import DRTextInput from '@/components/DRTextInput.vue'
import DRDropDown from '@/components/DRDropDown.vue'
import DRButton from '@/components/DRButton.vue'
import DRCheckBox from '@/components/DRCheckBox.vue'
import { ref } from 'vue'

const VALID_COLUMN_TYPES = ['STRING', 'INTEGER', 'FLOAT', 'BOOLEAN'] as const
type ValidColumnTypes = typeof VALID_COLUMN_TYPES[number]

type ColumnFileData = {
  index: number
  name: string
}
const fileDataList = ref<ColumnFileData[]>([])

type ColumnConfig = {
	index: number
	name: string
	type: ValidColumnTypes
	canBeNull: boolean
	description: string
}
const columnConfigList = ref<ColumnConfig[]>([])
const selectColumnString = ref<string>('')

const getUnconfiguredColumns = () => {
	const configuredColumnIndexList = columnConfigList.value.map(columnConfig => columnConfig.index)
	return fileDataList.value.filter(fileData => !configuredColumnIndexList.includes(fileData.index))
}

const removeColumnConfig = (index: number) => {
	columnConfigList.value.splice(index, 1)
}

const addColumnConfig = (configIndex: number) => {
	const fileData = fileDataList.value.find(value => value.index === configIndex)
	if(!fileData) throw new Error(`No fileData with "${configIndex}" found.`)
	if(columnConfigList.value.find(value => fileData.index === value.index)) throw new Error(`FileData with index "${configIndex}" already added.`)

	columnConfigList.value.push(Object.assign({
		type: 'STRING' as ValidColumnTypes,
		canBeNull: false,
		description: '',
	}, fileData))
}

const uploadFile = () => {
	// MOCKED
	fileDataList.value = []
	fileDataList.value = [
		{
			index: 0,
			name: 'NOME',
		},
		{
			index: 1,
			name: 'VALOR',
		},
	]

	columnConfigList.value = []
	fileDataList.value.forEach(value => {
		addColumnConfig(value.index)
	})
}


</script>

<template>
  <AppHeader></AppHeader>
  <main>
    <DRSectionTitle title="Upload"></DRSectionTitle>
    <div class="wrapper flex">
      <DRDropDown title="Tipo" :option-list="['csv']"></DRDropDown>
      <DRButton :click-behavior="uploadFile">Upload</DRButton>
    </div>
    <DRSectionTitle title="Metadata"></DRSectionTitle>
    <div class="grid metadata">
      <DRTextInput style="grid-area: config-name;" title="Nome da Configuração"></DRTextInput>
      <DRDropDown style="grid-area: has-header;" title="Terá Header" :option-list="['Sim', 'Não']"></DRDropDown>
      <DRTextInput style="grid-area: file-name;" title="Nome do Arquivo"></DRTextInput>
      <DRTextInput style="grid-area: origin;" title="Origem"></DRTextInput>
      <DRTextInput size="small" style="grid-area: separator;" title="Separador"></DRTextInput>
      <DRTextInput style="grid-area: frequency;" title="Frequencia"></DRTextInput>
      <DRDropDown style="grid-area: period;" title="Período" :option-list="['Dia', 'Mês', 'Ano']"></DRDropDown>
    </div>
    <div class="wrapper container" v-if="fileDataList.length > 0" :key="`${fileDataList.length}_${columnConfigList.length}`">
    <DRSectionTitle style="grid-area: title;" title="Configurar Colunas"></DRSectionTitle>
			<div class="wrapper flex add-column">
				<DRDropDown
					title="Adicionar Nova Coluna"
					:option-list="getUnconfiguredColumns().map(c => `${c.index} ${c.name}`)"
					:on-change="(value: string) => selectColumnString = value"
					:key="columnConfigList.length"
				>
				</DRDropDown>
				<DRButton
					:click-behavior="() => {
						const index = selectColumnString.split(' ')[0]
						addColumnConfig(Number(index))
					}"
					:disabled="getUnconfiguredColumns().length === 0"
				>Adicionar</DRButton>
			</div>
			<div class="grid column-config" v-for="columnConfig, index in columnConfigList" :key="index">
				<DRTextInput style="grid-area: name;" title="Nome Coluna" :default-value="columnConfig.name"></DRTextInput>
				<DRTextInput style="grid-area: index;" title="Numero da coluna" :default-value="columnConfig.index"></DRTextInput>
				<DRDropDown style="grid-area: type;" title="Tipo" :option-list="VALID_COLUMN_TYPES"></DRDropDown>
				<DRCheckBox style="grid-area: can-null;" title="Pode Nulo?"></DRCheckBox>
				<DRButton style="grid-area: remove;" button-type="careful" :click-behavior="() => removeColumnConfig(index)">Remover</DRButton>
				<DRTextInput style="grid-area: description;" :is-text-area="true" :custom-height="7" title="Descrição"></DRTextInput>
			</div>
			<div class="wrapper save">
				<DRButton button-type="safe" :click-behavior="() => {}">Save</DRButton>
			</div>
		</div>
  </main>
</template>

<style scoped lang="scss">
.save {
  padding: var(--big-gap);
  background-color: var(--color-background);
  position:relative;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: flex-end;
  width: 100%;
}
.wrapper.container {
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}
.wrapper.flex {
  display: flex;
  padding: var(--big-gap) 0;
  align-items: last baseline;
  gap: var(--big-gap);
  &.add-column {
    border-bottom: 1px solid var(--color-separator);
  }

}
.grid {
  display: grid;
  column-gap: var(--big-gap);
  row-gap: var(--gap);
  align-items:last baseline;
  justify-content: flex-start;
  padding: var(--big-gap) 0;
}
.metadata {

  grid-template-columns: min-content min-content min-content;
  grid-template-rows: min-content min-content min-content;
  grid-template-areas:
    "file-name file-name file-name"
    "config-name config-name has-header"
    "origin origin separator"
    "frequency period .";

}

.column-config {
  grid-template-columns: min-content min-content min-content min-content min-content;
  grid-template-rows: min-content 2fr;
  grid-template-areas:
    "index name type can-null remove"
    "description description description description description";
}
main {
  position: relative;
  padding: var(--big-gap);
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
</style>
