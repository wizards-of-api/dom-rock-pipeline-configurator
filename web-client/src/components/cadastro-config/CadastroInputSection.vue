<script setup lang="ts">
import { onMounted, ref } from 'vue'
import DRDropDown from '../DRDropDown.vue'
import DRCheckBox from '../DRCheckBox.vue'
import DRTextInput from '../DRTextInput.vue'
import DRButton from '../DRButton.vue'
import api from '@/JwtToken/token'
import router from '@/router'
import type { EmpresaConfig } from '@/components/lz-config/types'

const empresaConfig = ref<EmpresaConfig[]>()

const emit = defineEmits(['update'])

const emitUpdate = () => {
	emit('update',wrapBooleanMetadata()),
	emit('update', wrapUpdateMetadata())
}

const nome = defineModel<string>('nome')
const email = defineModel<string>('email')
const lzbool = defineModel<boolean>('lzbool')
const bronzebool = defineModel<boolean>('bronzebool')
const silverbool = defineModel<boolean>('silverbool')
const permission = defineModel<number>('permission') // valor padrão inicial
const senha = defineModel<string>('senha')
const empresa = defineModel<string>('empresa')

const companyLists = empresaConfig?.value?.map(columns => columns)
const mapOptions = (column: EmpresaConfig) => `${column.fantasyName}`


const saveFile = async () => {
	try{
		await api.put('/user/create-user',
			{
				name:nome.value,
				email:email.value,
				password:senha.value,
				empresa: empresa.value,
				lzbool: lzbool.value,
				bronzebool: bronzebool.value,
				silverbool: silverbool.value,
			},
		)
	}catch(erro){
    	router.replace(`/login`)
  	}
}

const getEmpresas = async () => {
	try {
		const response = await api.get(`/company/getAllCompanies`)
		return response.data
	} catch (error) {
		router.replace(`/login`)
	}
}

onMounted(async () => {
	empresaConfig.value = await getEmpresas()
})

const wrapBooleanMetadata = () => {
	let perm = 2 // valor padrão

	if (bronzebool.value === false && lzbool.value === true && silverbool.value === false) {
		perm = 2
	} if (bronzebool.value === true && lzbool.value === true && silverbool.value === false){
		perm = 3
	} if (bronzebool.value === false && lzbool.value === true && silverbool.value === true){
		perm = 4
	} if (bronzebool.value === true && lzbool.value === true && silverbool.value === true){
		perm = 5
	} if (bronzebool.value === true && lzbool.value === false && silverbool.value === false){
		perm = 6
	} if (bronzebool.value === true && lzbool.value === true && silverbool.value === false){
		perm = 7
	} if (bronzebool.value === false && lzbool.value === false && silverbool.value === true) {
		perm = 8
	}

	permission.value = perm // Atualiza o valor de permission
}

const wrapUpdateMetadata = () => ({
	nome: nome.value,
	email: email.value,
	senha: senha.value,
	empresa: empresa.value,
})

</script>

<template>
	<div style="width: 100%">
		<div class="grid cadastro">
			<DRTextInput
				style="grid-area: nome; width: 14rem"
				title="Nome"
				v-model="nome"
				@update="emitUpdate"
			></DRTextInput>
			<DRTextInput
				style="grid-area: senha; width: 14rem"
				title="Senha"
				v-model="senha"
				@update="emitUpdate"
			></DRTextInput>
			<DRCheckBox
				style="grid-area: lz; width: 2rem;"
				title="LZ"
				v-model="lzbool"
				@update="emitUpdate"
			></DRCheckBox>
			<DRCheckBox
				style="grid-area: bronze; width: 5rem;"
				title="Bronze"
				v-model="bronzebool"
				@update="emitUpdate"
			></DRCheckBox>
			<DRCheckBox
				style="grid-area: silver; width: 5rem;"
				title="Silver"
				v-model="silverbool"
				@update="emitUpdate"
			></DRCheckBox>
			<DRTextInput
				style="grid-area: email; width: 14rem"
				title="Email"
				v-model="email"
				@update="emitUpdate"
			></DRTextInput>
			<DRDropDown
				style="grid-area: empresas; width: 14rem"
				title="Empresa"
				:option-list="empresaConfig?.map(mapOptions)"
				v-model="empresa"
				@update="emitUpdate"
			></DRDropDown>
			<div class="saveButton">
				<DRButton :click-behavior="saveFile" v-bind:disabled="false">Cadastrar</DRButton>
			</div>
		</div>
	</div>
</template>

<style scoped lang="scss">
.saveButton{
	margin: -80px;
}

.grid {
	display: grid;
	column-gap: var(--big-gap);
	row-gap: var(--gap);
	justify-content: flex-start;
	align-items: last baseline;
	overflow-y: hidden;
}
.cadastro {
	grid-template-columns: min-content min-content min-content min-content min-content;
	grid-template-rows: min-content min-content;
	grid-template-areas:
		'nome senha lz bronze silver'
		'email empresas empresas empresas upload';
}
.checkBox{
	display: flex;
	align-items: last baseline;
	flex-direction: row;
	justify-content: flex-start;
}
</style>
