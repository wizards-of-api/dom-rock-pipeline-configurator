<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'
import type { BronzeConfig } from './types';
type Props = {
    config?: BronzeConfig
}
const {config} = defineProps<Props>()
</script>
<template>
	<DRSectionTitle title="Metadata"></DRSectionTitle>
	<main  v-if="config">
		<div class="containerMetadata">
			<div class="titleInfo">
				<div class="textInfo">
					<p class="sizeWord">Nome da configuração:</p>
					<p class="sizeWord">{{config.name}}</p>
				</div>
				<div class="textInfo">
					<p class="sizeWord">Arquivo:</p>
					<p class="sizeWord">{{config.fileName}}</p>
				</div>	
			</div>
			<div class="titleInfo">
				<div class="textInfo">
					<p class="sizeWord">Origem:</p>
					<p class="sizeWord">{{config.fileOrigin}}</p>
				</div>
				<div class="textInfo">	
					<p class="sizeWord">Frequencia:</p>
					<p class="sizeWord">{{config.frequency}}</p>
				</div>
			</div>
	    </div>
	</main>
</template>
<style scoped lang="scss">
main{
	width:100vh;
	margin-top:10px;
}
.containerMetadata{
	display:flex;
	flex-direction: row;
	justify-content: space-between;
}
.titleInfo{
	display:flex;
	flex-direction: column;
	gap:10px;
}
.sizeWord{
	font-size:20px;
}
.textInfo{
	display:flex;
	flex-direction: row;
	gap:10px;
}
</style>
