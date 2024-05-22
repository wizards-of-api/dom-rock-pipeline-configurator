<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'
import {  type SilverConfig } from '@/components/silver/types'
import { type silverFromTo } from '@/components/silver/types'
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import DRModal from '@/components/DRModal.vue'
import MetadataSilver from '@/components/silver/ColumnSelectionSilver.vue'
import FromTo from '@/components/silver/FromToFile.vue'
import LZModalLeave from '@/components/lz-config/LZModalLeave.vue'
import LZModalSaved from '@/components/lz-config/LZModalSaved.vue'
import SilverColumnRow from '@/components/silver/SilverColumnRow.vue'
import Load from '@/components/Load.vue'
import router from '@/router'

const config = ref<SilverConfig>()
const silverdata = ref<silverFromTo>()
const showLeaveModal = ref(false)
const showSavedModal = ref(false)
const getConfig = async () => {
	const response = await axios.get(`http://localhost:8080/silver-config/${router.currentRoute.value.params.id}`)
	return response.data
}
onMounted(async () => {
	config.value = await getConfig()
})

const saveFile = async () => {
	await axios.put(`http://localhost:8080/silver-config/update/${router.currentRoute.value.params.id}`, config.value)
	router.replace(`/list-view-silver`)
}
	
	onMounted(async () => {
    config.value = await getConfig()
    silverdata.value = config.value
})

</script>
<template>
	<div>
		<div>
			<DRModal :show="showLeaveModal" @click-out="showLeaveModal = false">
				<LZModalLeave :close-modal="() => showLeaveModal = false"></LZModalLeave>
			</DRModal>
			<DRModal :show="showSavedModal">
				<LZModalSaved></LZModalSaved>
			</DRModal>
		</div>
		<div style="max-height: 100vh; overflow-y: scroll;">
			<AppHeader>
			</AppHeader>
			<nav class="wrapper nav">
				<DRButton :click-behavior="() => showLeaveModal = true">Voltar</DRButton>
				<DRButton button-type="safe" :click-behavior="saveFile">Salvar</DRButton>
			</nav>
			<main>
				<MetadataSilver></MetadataSilver>
				<FromTo></FromTo>
				<SilverColumnRow :base-from-to="silverdata"></SilverColumnRow>
			</main>
		</div>
	</div>
</template>

<style scoped lang="scss">
.nav {
	padding: var(--gap) var(--big-gap);
	background: var(--color-background-mute);
	position:sticky;
	top: 0;
	z-index: 10;

	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 100%;
	border-bottom: 1px solid var(--color-separator);
}
main {
	margin: 0 auto;
	border: 1px solid var(--color-separator);
	border-top: 0;
	border-bottom: 0;
	width: 100%;
	max-width: 1280px;
	padding: var(--big-gap);
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	gap:30px;
}
</style>
