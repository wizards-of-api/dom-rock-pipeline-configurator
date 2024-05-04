<script setup lang="ts">
import type { LZConfig, BConfig } from '../../lz-config/types'

type Props = {
	configList?: LZConfig[]
    onBannerClick: (index: LZConfig) => void
	tagInfo?: 'invalid-tag' | 'valid-tag'
	bronzeConfig?: BConfig
}
const { configList, onBannerClick } = defineProps<Props>()


const validOrInvalid = (column:BConfig ) =>{
	const validation = column?.columns.filter((column) => column.valid === 1)
	const statuscolumn = column?.columns.filter((column)=> column.status === 1)
	const hashVerify = column?.columns.reduce((string, column) => {
		if (!column.hash) return string
		string += `${column.columnName}, `
		return string
	}, '')
	
	const valid = (validation.length === statuscolumn.length && hashVerify !== "") ? true : false
	return valid
}
</script>
<template>
    <div class="container">
        <h2>Bronze</h2>
        <div class="grid-wrap" v-if="configList">
            <button class="banner" v-for="config in configList" :key="config.fileId" @click="onBannerClick(config)">
				<div class = "banner-wrap">
					<div :class=" validOrInvalid(config) ? 'valid-tag' : 'invalid-tag'"></div>
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
	grid-template-rows: 10px, 1fr;
	gap: var(--gap) var(--gap);
	justify-content:flex-start;
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
.invalid-tag{
	background: #f00;
	border-radius:50%;
	width: 10px;
	height: 10px;
}
.valid-tag{
	background: rgb(0, 255, 0);
	border-radius:50%;
	width: 10px;
	height: 10px;
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
