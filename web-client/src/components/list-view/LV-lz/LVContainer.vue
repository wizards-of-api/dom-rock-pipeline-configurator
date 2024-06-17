<script setup lang="ts">
import DRButton from '@/components/DRButton.vue'
import router from '@/router'
import type { LZConfig } from '@/components/lz-config/types'
import DRSearch from '@/components/DRSearch.vue'
import {onMounted, ref} from 'vue'
import api from '@/JwtToken/token'

type Props = {
    configList: LZConfig[]
    onBannerClick: (index: LZConfig) => void
}

// eslint-disable-next-line vue/no-dupe-keys
const configList = ref<LZConfig[]>([])
let {onBannerClick} = defineProps<Props>()

const goToLZConfig = () => {
	router.replace('/lz-config')
}

onMounted(() => {
	getAllPages()
})

async function getAllPages() {
	try {
		const cnpj = localStorage.getItem('cnpj')
		const response = await api.get(`/lz-config/list-view/company/${cnpj}`)
		const configListCompleted: LZConfig[] = []
		configListCompleted.push(...response.data)
		configList.value = configListCompleted
	} catch (error) {
		router.replace('/login')
	}
}

const handleSearch = async (updateSearchTerm: string) => {
	if (!updateSearchTerm || updateSearchTerm == "") {
		await getAllPages()
	} else {
		try {
			const response = await api.get(`/lz-config/list-view/${updateSearchTerm}`)
			configList.value = response.data
			return configList.value
		} catch (error) {
			router.replace('/login')
		}
	}
}

</script>
<template>
    <div class="container">
        <h2>Landing Zone</h2>
		<div class = -bar>
			<DRSearch @updateSearchTerm="handleSearch"> </DRSearch>
			<DRButton :click-behavior="goToLZConfig" v-bind:disabled="false">Registrar</DRButton>
		</div>
			<div class="grid-wrap" v-if=configList>
				<button class="banner" v-for="config in configList" :key="config.fileId" @click="onBannerClick(config)">
					{{ config.name }}
				</button>
        </div>
    </div>

</template>

<style scoped lang="scss">
.grid-wrap {
	padding: var(--gap) 0;
	display: grid;
	grid-template-columns: 1fr 1fr 1fr 1fr;
	grid-template-rows: 1fr 1fr 1fr;
	gap: var(--gap) var(--gap);
	justify-content: space-between;
	align-items: center;
	flex-wrap: wrap;
	justify-items: center;
	overflow-y: hidden;
}
.top-bar {
  display: flex;
  justify-content: flex-end;
  
}

.-bar {
	display: flex;
  	justify-content:space-between;
}

.banner {
	background: var(--color-banner);
	border-color: var(--color-banner);
	color: var(--color-banner-text);
  	outline-color: var(--color-banner-text);
	font-size: 1.4rem;
	height: 92px ;
	width: 200px;
	overflow: hidden;
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
</style>
