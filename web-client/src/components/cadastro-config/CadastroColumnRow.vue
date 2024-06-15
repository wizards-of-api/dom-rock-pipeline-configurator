<script setup lang="ts">
import DRTextInput from '../DRTextInput.vue'
import { onMounted } from 'vue'
import DRButton from '../DRButton.vue'
import api from '@/JwtToken/token'
import router from '@/router'
import type { CadastroConfig } from '@/components/lz-config/types'


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

const deleteFromTo = async (email:String | undefined) => {
	if(email){
		try {
			await api.delete(`/silver-config/delete/${email}`)
			location.reload()
		} catch (error) {
			router.replace('/login')
		}
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
		<DRTextInput
			style="grid-area: from; width: 16rem;"
			title="Permission"
			v-model="permission"
			@update="emitUpdate"
		></DRTextInput>
		<DRButton
			style="grid-area: delete; width: 16rem;"
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
	grid-template-columns: 28% 28% 20% 20% 20%;
	grid-template-rows: min-content 1fr;
	grid-template-areas:
		'name from delete';
}
.enterprise_register{
	margin-top: 5%;
	display: flex;
	flex-direction: column;
	gap: 30px;
}
</style>
