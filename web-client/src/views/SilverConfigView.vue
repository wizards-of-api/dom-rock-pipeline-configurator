<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'
import {  type SilverConfig } from '@/components/silver/types'
import AppHeader from '@/components/AppHeader.vue'
import DRButton from '@/components/DRButton.vue'
import MetadataSilver from '@/components/silver/ColumnSelectionSilver.vue'
import FromTo from '@/components/silver/FromToFile.vue'
import router from '@/router'
import type { LZConfigView } from '@/components/lz-config/types'
import ImportantMessage from '@/components/ImportantMessage.vue'
import api from '@/JwtToken/token'

const configAll = ref<SilverConfig[]>([])
const fileConfig = ref<LZConfigView>()
const showLeaveModal = ref(false)

const getAllConfigs = async () => {
	try {
		const response = await api.get(`/silver-config/get-by-fileid/${router.currentRoute.value.params.id}`)
		return response.data
		
	} catch (error) {
		router.replace("/login")
	}
}

const goToListViewSilver = () => {
	return router.replace('/list-view-silver')
} 

const getAllColumns = async () => {
	try {
		const response = await api.get(`/lz-config/${router.currentRoute.value.params.id}`)
		return response.data
	} catch (error) {
		router.replace("/login")
	}
}

onMounted(async () => {
	fileConfig.value = await getAllColumns()
	configAll.value = await getAllConfigs()
})
</script>
<template>
	<div v-if="fileConfig?.columns">
		<div style="max-height: 100vh; overflow-y: scroll;">
			<AppHeader>
			</AppHeader>
			<nav class="wrapper nav">
				<DRButton :click-behavior="goToListViewSilver">Voltar</DRButton>
			</nav>
			<main>
				<MetadataSilver :file-config="fileConfig"></MetadataSilver>
				<div v-if="configAll.length === 0">
					<ImportantMessage></ImportantMessage>
				</div>
				<FromTo v-if="configAll.length>0" :base-column-list="configAll"></FromTo>
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
