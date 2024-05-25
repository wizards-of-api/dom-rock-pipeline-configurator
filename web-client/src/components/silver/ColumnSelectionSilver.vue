<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import type { SilverConfig } from './types';
import type { silverFromTo } from './types';
import DRDropDown from '../DRDropDown.vue';
import DRTextInput from '../DRTextInput.vue';
import type { ColumnConfig }from './types';
import DRButton from '../DRButton.vue';
import { onMounted, ref } from 'vue';
import axios from 'axios';
import router from '@/router';

const emit = defineEmits(['update'])
const emitUpdate = () => {
	emit('update')
}

const config = ref<ColumnConfig>()
const columnIndex = defineModel<string>('index')
const fromC = defineModel<string>('fromC')
const toC = defineModel<string>('toC')

type Props = {
    config?: SilverConfig[]
	silverData?: silverFromTo
}

const clickTest = () => {
	emit('update', wrapUpdateMetadata())
}
const saveFile = async () => {
	await axios.post(`http://localhost:8080/silver-config/save/${router.currentRoute.value.params.id}`, {
		silverId: null,
		columnId: columnIndex.value,
		from:fromC.value,
		to:toC.value,
	})}


const wrapUpdateMetadata = () => ({
	silverIs: null,
	columnIndex: columnIndex.value === 'Código' ? 1:1,
	fromC: fromC.value,
	toC: toC.value,
})


</script>
<template>
	<DRSectionTitle title="Nome_Da_Configuração"></DRSectionTitle>
	<main>
		<div class="containerMetadata">
			<div class="titleInfo">
					<DRDropDown 
            			title="Coluna"
						v-model="columnIndex"
            			:option-list="['Código', 'Marca', 'Aparelho', 'Etc...']"
						@update="clickTest"
						></DRDropDown>
					<div class="buttonAlign">
						<DRButton 
						:click-behavior="saveFile">Adcionar
						</DRButton>
						</div>
					<div class="rowMetadata">
						<div class="textInfo">
						<DRTextInput
						style="grid-area: index"
            			title="De"
            			v-model="fromC"
						@update="clickTest"
            			></DRTextInput>
						</div>
						<div class="textInfo">
						<DRTextInput
						style="grid-area: index"
            			title="Para"
						@update="clickTest"
            			v-model="toC"
            			></DRTextInput>
						</div>
					</div>
					
				</div>	
			</div>
	</main>
</template>
<style scoped lang="scss">
main{
	width:100vh;
	margin-top:10px;
}
.containerMetadata{
	display:flex;
	flex-direction: row;
	justify-content: space-between;
}
.buttonAlign{
	display:flex;
	width: 400%;
	height: 50px;
    flex-direction: row;
    justify-content: end;
}
.rowMetadata{
	display:grid;
	grid-template-columns: 1fr 1fr 100px 1fr;
	grid-template-rows:1fr;
	align-items: center;
	column-gap: 25%;
    flex-direction: column;
    justify-content: space-between;
}
.titleInfo{
	display:flex;
	width: 30%;
	flex-direction: column;
	gap:10px;
}
.sizeWord{
	font-size:20px;
}
.textInfo{
	display:flex;
	flex-direction: row;
	gap:10px;
}

</style>
