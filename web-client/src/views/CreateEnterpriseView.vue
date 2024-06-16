<script setup lang="ts">
import api from '@/JwtToken/token'
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import ColumnSelectionEnterprise from '@/components/cadastro-config/ColumnSelectionEnterprise.vue'
import type { EnterpriseModel } from '@/components/silver/types'
import router from '@/router'
import { onMounted, ref } from 'vue'

const configAll = ref<EnterpriseModel[]>([])
const nome = defineModel<string>('nome')
const cnpj = defineModel<string>('cnpj')

const saveFile = async()=>{
	if(nome.value && cnpj.value) {
		const company = {
			cnpj: cnpj.value,
			fantasyName: nome.value,
		}
		try {
			await api.post(`/company/create-company`, company)
			location.reload()
		} catch (error) {
			router.replace("login")
		}
	}else{
		alert("Preencha todos os campos")
	}
}

const getAllEnterprise = async () => {
	try {
		const response = await api.get(`/company/getAllCompanies`)
		return response.data
	} catch (error) {
		await router.replace("login")
	}
	
}
onMounted(async () => {
	configAll.value = await getAllEnterprise()
})

</script>
<template>
	<div style="width: 100%">
		<AppHeader>
		</AppHeader>
		<div class="container">
			<main>
				<div class="input_name">
					<h3>Nome da empresa:</h3>
					<input
					type="text"
					class="nome"
					placeholder="nome"
					v-model="nome"
					>
				</div>
				<div class="input_cnpj">
					<h3>CNPJ:</h3>
					<input 
						type="text"
						class="cnpj"
						placeholder="cnpj"
						v-model="cnpj"
					>
				</div>
				<div class="enterprise_list">
					<div class="button_register">
						<DRButton :click-behavior="saveFile" size="large" v-bind:disabled="false">Adicionar</DRButton>
					</div>
				</div>
			</main>
			<div class="enterprise_register" v-if="configAll">
				<h2>Lista de Empresas:</h2>
				<ColumnSelectionEnterprise :columns="configAll"></ColumnSelectionEnterprise>
			</div>
		</div>
	</div>
</template>
<style scoped lang="scss">
main {
	margin-top:5%;
	display: flex;
	flex-direction: row;
	align-items: flex-end;
	justify-content:flex-start;
	gap:50px;
}

.container {
	width: 80%;
	height: 100vh;
	margin:auto;
	border: 1px solid var(--color-separator);
	border-radius: var(--border-radius);
	padding: var(--gap) var(--big-gap);
	display: flex;
	flex-direction: column;
	gap: var(--gap);
}
.data_container{
	display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

input{
  width: 410px;
  height: 40px;
  border-radius: 8px;
  border: 1px solid #ffffff;
  padding: 5px;
}

.enterprise_list{
	display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
}

.enterprise_register{
	margin-top: 5%;
	display: flex;
    flex-direction: column;
	gap: 30px;
	height: 100Vh;
	overflow-x: hidden;
}

.input_name, .input_cnpj{
	display: flex;
    flex-direction: column;
    align-items: flex-start;
	gap: 10px;
}

</style>
