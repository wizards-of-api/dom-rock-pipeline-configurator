<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'
import type {  ColumnConfig, BronzeConfig } from '@/components/bronze/types'
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import DRModal from '@/components/DRModal.vue'
import MetadataBronze from '@/components/bronze/MetadataBronze.vue'
import ValidatorHashFile from '@/components/bronze/ValidatorHashFile.vue'
import LZModalLeave from '@/components/lz-config/LZModalLeave.vue'
import LZModalSaved from '@/components/lz-config/LZModalSaved.vue'


let columnList: ColumnConfig[] = []
const columnUpdateCount = ref(0)
const updateColumnList = (newList: ColumnConfig[]) => {
	onUpdateColumn(newList)
	columnUpdateCount.value += 1
}
const config = ref<BronzeConfig>()
const idConfig = ref<number>()

const showLeaveModal = ref(false)
const showSavedModal = ref(false)


const onUpdateColumn = (newColumnList: ColumnConfig[]) => {
	columnList = newColumnList
	console.log(columnList)
}

const getConfig = async () => {
	const response = await axios.get('http://localhost:8080/bronze-config/1')
	console.log("valor ->",response)
	return response.data
}

onMounted(async () => {
	
	config.value = await getConfig()
	console.log(config.value)
})


const saveFile = async () => {
	const config = {
		columns: columnList,
	}

	await axios.put(`http://localhost:8080/lz-config/update/${idConfig}`, config)
	showSavedModal.value = true
}
</script>

<template>
	<div>
		<div>
			<DRModal :show="showLeaveModal" @click-out="showLeaveModal = false">
				<LZModalLeave :close-modal="() => showLeaveModal = false"></LZModalLeave>
			</DRModal>
			<DRModal :show="showSavedModal">
				<LZModalSaved></LZModalSaved>
			</DRModal>
		</div>
		<div style="max-height: 100vh; overflow-y: scroll;">
			<AppHeader>
			</AppHeader>
			<nav class="wrapper nav">
				<DRButton :click-behavior="() => showLeaveModal = true">Voltar</DRButton>
				<DRButton button-type="safe" :click-behavior="saveFile">Salvar</DRButton>
			</nav>
			<main>
				<MetadataBronze :config="config"></MetadataBronze>
				<ValidatorHashFile :base-column-list="columnList" @update="onUpdateColumn" :key="columnUpdateCount"></ValidatorHashFile>
			</main>
		</div>
	</div>
</template>

<style scoped lang="scss">
.nav {
	padding: var(--gap) var(--big-gap);
	background: var(--color-background-mute);
	position:sticky;
	top: 0;
	z-index: 10;

	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 100%;
	border-bottom: 1px solid var(--color-separator);
}
main {
	margin: 0 auto;
	border: 1px solid var(--color-separator);
	border-top: 0;
	border-bottom: 0;
	width: 100%;
	max-width: 1280px;
	position: relative;
	padding: var(--big-gap);
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}
</style>
