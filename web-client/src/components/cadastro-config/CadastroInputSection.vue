<script setup lang="ts">
import { onMounted, ref } from 'vue'
import DRDropDown from '../DRDropDown.vue'
import DRTextInput from '../DRTextInput.vue'
import DRButton from '../DRButton.vue'
import axios from 'axios'

import type { CadastroConfig } from '@/components/lz-config/types'

const config = ref<CadastroConfig>()
const showModal = ref(false)
const selectedConfig = ref<CadastroConfig>()


const nome = defineModel<String>('nome')
const email = defineModel<String>('email')
const permissoes = defineModel<String[]>('permissoes')
const senha = defineModel<String>('senha')
const isSuper = defineModel<boolean>('isSuper')


const saveFile = async () => {
	await axios.post('http://localhost:8080/user/create-user', {
		name:nome.value,
		email:email.value,
		password:senha.value,
		permissoes:{permissionId: permissoes.value},
		isSuper:true,

	})}

const wrapUpdateMetadata = () => ({
	nome: nome.value,
	email: email.value,
	senha:senha.value,
	permissoes: permissoes.value,
	isSuper: isSuper.value,
})


</script>
<template>
	<div  style="width: 100%" >
		<div class="grid cadastro">
			<DRTextInput
			style="grid-area: nome; width: 16rem"
				title="Nome"
				v-model="nome"
			></DRTextInput>
			<DRTextInput
				style="grid-area: senha; width: 16rem"
				title="Senha"
				v-model="senha"
			></DRTextInput>
			<DRDropDown
				style="grid-area: permissoes; width: 16rem"
				title="Permissões"
				:option-list="['LZ', 'Bronze', 'Silve']"
				v-model="permissoes"
			></DRDropDown>
			<DRTextInput
				style="grid-area: email; width: 16rem"
				title="Email"
				v-model="email"
			></DRTextInput>
			<DRDropDown
			style="grid-area: empresas; width: 16rem"
			title="Empresa"
			:option-list="'two'"
			v-model="permissoes"
		></DRDropDown>
		<DRButton :click-behavior="saveFile">Cadastrar</DRButton>
		</div>
	</div>
</template>
<style scoped lang="scss">
.grid {
	display: grid;
	column-gap: var(--big-gap);
	row-gap: var(--gap);
	justify-content: flex-start;
	align-items: last baseline;
	overflow-y: hidden;
}
.cadastro {
	grid-template-columns: min-content min-content;
	grid-template-rows: min-content min-content;
	grid-template-areas:
		'nome senha permissoes'
		'email empresas upload';
}
</style>
