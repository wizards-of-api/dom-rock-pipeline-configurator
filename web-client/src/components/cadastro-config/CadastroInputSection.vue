<script setup lang="ts">
import { onMounted, ref } from 'vue'
import DRDropDown from '../DRDropDown.vue'
import DRCheckBox from '../DRCheckBox.vue'
import DRTextInput from '../DRTextInput.vue'
import DRButton from '../DRButton.vue'
import axios from 'axios'

import type { CadastroConfig, EmpresaConfig, LZConfigView } from '@/components/lz-config/types'
import type { ColumnConfig } from '@/components/silver/types'
const config = ref<CadastroConfig>()
const showModal = ref(false)
const selectedConfig = ref<CadastroConfig>()

const empresaConfig = ref<EmpresaConfig[]>()

const emit = defineEmits(['update'])

const emitUpdate = () => {
	emit('update', wrapUpdateMetadata())
}


const nome = defineModel<string>('nome')
const email = defineModel<string>('email')
const lzbool = defineModel<boolean>('lzbool')
const bronzebool = defineModel<boolean>('bronzebool')
const silverbool = defineModel<boolean>('silverbool')
const senha = defineModel<string>('senha')
const isSuper = defineModel<boolean>('isSuper')
const empresa = defineModel<string>('empresa')

const companyLists = empresaConfig?.value?.map(columns => columns)
const mapOptions = (column: EmpresaConfig) => `${column.fantasyName}`

const header = {

}

const saveFile = async () => {
	await axios.put('http://localhost:8080/user/create-user',
		{
			name:nome.value,
			email:email.value,
			password:senha.value,
			isSuper:null,
			empresa: empresa.value,
			lzbool: lzbool.value,
			bronzebool: bronzebool.value,
			silverbool: silverbool.value,
		}, {
			headers: {
				'Content-Type': 'application/json',
				'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJwZXJtaXNzaW9uSUQiOjEsInVzZXJFbWFpbCI6ImFhYW11bGVrbGVrbGVrbGVrQHdpei5jb20iLCJzdWIiOiJhYWFtdWxla2xla2xla2xla0B3aXouY29tIiwiaWF0IjoxNzE4MjMxNDUxLCJleHAiOjE3MTgyMzUwNTF9.EF8AIAp9krjMz-ebwXhAFwQKGIEhfSlWHhg41ePj11M'
			},
		},
)}
const getEmpresas = async () => {
	const response = await axios.get(`http://localhost:8080/company/getAllCompanies`)
	return response.data

}
onMounted(async () => {
	empresaConfig.value = await getEmpresas()
})

const wrapUpdateMetadata = () => ({
	nome: nome.value,
	email: email.value,
	senha:senha.value,
	permissoes: [lzbool.value,bronzebool.value,silverbool.value],
	isSuper: isSuper.value,
	empresa: empresa.value,

})


</script>
<template>
	<div  style="width: 100%" >
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
			<DRButton :click-behavior="saveFile">Cadastrar</DRButton>
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
