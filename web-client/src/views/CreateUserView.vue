<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import { onMounted, ref } from 'vue'
import type { CadastroConfig } from '@/components/lz-config/types'
import CadastroInputSection from '@/components/cadastro-config/CadastroInputSection.vue'
import ColumnSelectionCadastro from '@/components/cadastro-config/ColumnSelectionCadastro.vue'
import api from '@/JwtToken/token'
import router from '@/router'

const configAll = ref<CadastroConfig[]>()

const getAllUsers = async () => {
	try {
		const response = await api.get(`/user/get-all-users`)
		return response.data
	} catch (error) {
		router.replace("login")
	}

}
onMounted(async () => {
	configAll.value = await getAllUsers()
})

</script>
<template>
	<div style="width: 100%">
		<AppHeader>
		</AppHeader>
			<div class="container">
				<main>
					<CadastroInputSection></CadastroInputSection>
				</main>
				<div class="user_register" v-if="configAll">
					<h2>Lista de Usuários:</h2>
					<ColumnSelectionCadastro :columns="configAll"></ColumnSelectionCadastro>
				</div>
			</div>
	</div>
</template>
<style scoped lang="scss">
main {
	margin-top:5%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content:center;
	gap:10px;
}

.container {
	width: 1000px;
	height: 200rem;
	background: var(--color-background-soft);
	margin: var(--big-gap) auto;
	border: 1px solid var(--color-separator);
	border-radius: var(--border-radius);
	padding: var(--gap) var(--big-gap);
	display: flex;
	flex-direction: column;
	gap: var(--gap);
}
.nav {
	display: flex;
	align-items: flex-end;
	justify-content: center;
	gap: 30px;
}

.user_register{
	overflow-x: auto;
	height: 500px;
	width: 100%;
	border: 1px solid #ccc;
	padding: 2%;
}
</style>
