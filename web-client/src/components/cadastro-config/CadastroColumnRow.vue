<script setup lang="ts">
import DRTextInput from '../DRTextInput.vue'
import { onMounted } from 'vue'
import DRButton from '../DRButton.vue'
import api from '@/JwtToken/token'
import router from '@/router'
import type { CadastroConfig } from '@/components/lz-config/types'
import DRDropDown from '@/components/DRDropDown.vue'


type Props = {
	column?: CadastroConfig,
}

const { column } = defineProps<Props>()
const emit = defineEmits(['update'])
const nome = defineModel<String>('nome')
const email = defineModel<String>('email')
const permission = defineModel<String>('permission')
const emitUpdate = () => {
	emit('update', wrapColumnConfig())
}

onMounted(() => {
	nome.value = column?.name
	email.value = column?.email
	permission.value = column?.permission
})
const wrapColumnConfig = () => ({
	nome : column?.name,
	email : column?.email,
	permission : column?.permission,
})

const wrapUpdateMetadata = () => ({
	nome: nome.value,
	email: email.value,
	permission: permission.value,
})
enum PermissionList {
	LZ = "LZ",
	LZ_BRONZE = "LZ-BRONZE",
	LZ_SILVER = "LZ-SILVER",
	LZ_BRONZE_SILVER = "LZ-BRONZE-SILVER",
	BRONZE = "BRONZE",
	BRONZE_SILVER = "BRONZE-SILVER",
	SILVER = "SILVER"
}
const permissionOptions = Object.values(PermissionList);


const deleteFromTo = async (email:String | undefined) => {
	if(email){
		try {
			await api.delete(`/user/deleteuser/${email}`)
			location.reload()
			
		} catch (error) {
			router.replace('/login')
		}
	}
}

const updateUser = async (email:String | undefined) => {
	try{
		await api.post('/user/updateuser', {
			name: nome.value,
			email: email,
			permission: permission.value,

		})
		location.reload()
	}catch(erro){
		router.replace(`/login`)
	}
}
</script>
<template>
	<div
		class="grid column-config"
		:key=" column?.email">
		<DRSectionTitle title="Usuários:"></DRSectionTitle>
		<DRTextInput
			style="grid-area: name; width: 16rem;"
			title="Nome"
			v-model="nome"
			@update="emitUpdate"
		></DRTextInput>
		<DRDropDown
			style="grid-area: permission; width: 16rem;"
			title="Permission"
			:option-list="permissionOptions"
			v-model="permission"
			@update="emitUpdate"
		></DRDropDown>
		<DRButton
			style="grid-area: alter; width: 9rem;"
			v-bind:disabled="false"
			button-type="home"
			:click-behavior="() => updateUser(column?.email)"
		>Alterar</DRButton>
		<DRButton
			style="grid-area: delete; width: 9rem;"
			v-bind:disabled="false"
			button-type="careful"
			:click-behavior="() => deleteFromTo(column?.email)"
		>Remover</DRButton>
	</div>
</template>
<style scoped lang="scss">
.grid {
	display: grid;
	column-gap: var(--big-gap);
	row-gap: var(--gap);
	align-items: last baseline;
	justify-content: flex-start;
}
.column-config {
	margin-top: 5%;
	width: 100%;
	grid-template-columns: 28% 28% 15% 15%;
	grid-template-rows: min-content 1fr;
	grid-template-areas:
		'name permission alter delete';
}
</style>
