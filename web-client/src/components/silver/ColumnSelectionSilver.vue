<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import type { SilverConfig } from './types';
import type { silverFromTo } from './types';
import DRDropDown from '../DRDropDown.vue';
import DRTextInput from '../DRTextInput.vue';
import DRButton from '../DRButton.vue';
import { onMounted, ref } from 'vue';

const emit = defineEmits(['update'])
const emitUpdate = () => {
	emit('update')
}

const toAdd = defineModel('toAdd', {
	get: (value: any) => {
		if(value === undefined) return value
		return Number(value.split(' ')[0])
	},
})
const Teste = ""

type Props = {
    config?: SilverConfig[]
	silverData?: silverFromTo
}

const clickTest = ()=>{
	(console.log('Adicionar'))

}


</script>
<template>
	<DRSectionTitle title="Nome_Da_Configuração"></DRSectionTitle>
	<main>
		<div class="containerMetadata">
			<div class="titleInfo">
					<DRDropDown 
            			title="Coluna"
						v-model="toAdd"
            			:option-list="[Teste, 'Código', 'Marca', 'Aparelho', 'Etc...']"
						></DRDropDown>
					<div class="buttonAlign">
						<DRButton 
						:click-behavior="clickTest">Adcionar
						</DRButton>
						</div>
					<div class ="rowMetadata">
						<div class="textInfo">
						<DRTextInput
						style="grid-area: index"
            			title="De"
            			v-model="Teste"
            			></DRTextInput>
						</div>
						<div class="textInfo">
						<DRTextInput
						style="grid-area: index"
            			title="Para"
            			v-model="Teste"
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
