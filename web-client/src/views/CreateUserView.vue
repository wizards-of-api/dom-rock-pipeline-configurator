<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import { ref } from 'vue'
import type { CadastroConfig } from '@/components/lz-config/types'
import CadastroInputSection from '@/components/cadastro-config/CadastroInputSection.vue'

const config = ref<CadastroConfig[]>()

</script>
<template>
	<div style="width: 100%">
		<AppHeader>
		</AppHeader>
			<div class="container">
				<main>
					<CadastroInputSection></CadastroInputSection>
				</main>
				<div v-if="!config">
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
	width: 960px;
	height: 540px;
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
</style>
