<script setup lang="ts">
import type { LZConfig, BConfig } from '../../lz-config/types'
import validImage from '@/assets/valid.png'
import invalidImage from '@/assets/invalid.png'

type Props = {
	configList?: LZConfig[]
    onBannerClick: (index: LZConfig) => void
	bronzeConfig?: BConfig
}
const { configList, onBannerClick } = defineProps<Props>()

const getImageSrc = (column: BConfig) => {
	const validation = column?.columns.filter((column) => column.valid === 1)
	const statuscolumn = column?.columns.filter((column)=> column.status === 1)
	const hashVerify = column?.columns.reduce((string, column) => {
		if (!column.hash) return string
		string += `${column.columnName}, `
		return string
	}, '')
	
	const isValid = (validation.length === statuscolumn.length && hashVerify !== "") ? true : false
	return isValid ? validImage : invalidImage
}
</script>
<template>
    <div class="container">
        <h2>Bronze</h2>
        <div class="grid-wrap" v-if="configList">
            <button class="banner" v-for="config in configList" :key="config.fileId" @click="onBannerClick(config)">
				<div class="banner-wrap">
					<img :src="getImageSrc(config)" alt="Validation Status" class="status-icon" />
					<div>{{ config.name }}</div> 
				</div>
            </button>
        </div>
    </div>
</template>

<style scoped lang="scss">
.banner-wrap {
	padding: var(--gap) 0;
	display: grid;
	grid-template-columns: 1fr;
	grid-template-rows: 10px 1fr; 
	gap: var(--gap) var(--gap);
	justify-content: flex-start;
	align-items: center;
	flex-wrap: wrap;
	justify-items: center;
	overflow-y: hidden;
}
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
.banner {
	background: var(--color-banner);
	border-color: var(--color-banner);
	color: var(--color-banner-text);
	outline-color: var(--color-banner-text);
	border-width: 3px;
	font-size: 1.4rem;
	height: 100px;
	width: 200px;
}
.status-icon {
	width:  20px; 
	height: 20px; 
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
