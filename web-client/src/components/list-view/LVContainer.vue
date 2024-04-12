<script setup lang="ts">
import DRButton from '../DRButton.vue'
import router from '@/router'

type ConfigResume = {
	index: number
	name: string
}
type Props = {
    configList: ConfigResume[]
    onBannerClick: (index: number) => void
}

const goToLZConfig = () => {
	router.replace('/lz-config')
}


const { configList, onBannerClick } = defineProps<Props>()
</script>
<template>
    <div class="container">
        <h2>Landing Zone</h2>
        <div class="top-bar">
            <DRButton :click-behavior="goToLZConfig">Registrar</DRButton>
        </div>
        <div class="grid-wrap" v-if="configList">
            <button class="banner" v-for="config in configList" :key="config.index" @click="onBannerClick(config.index)">
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
	flex-wrap: wrap;
	justify-content: center;
	overflow-y: scroll;
}
.top-bar {
  display: flex;
  justify-content: flex-end;
}
.banner {
	background: var(--color-banner);
	color: var(--color-banner-text);
	font-size: 1.4rem;
	height: 100px;
	width: 200px;
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
