<script setup lang="ts">
import router from '@/router'
import type { validatedBronze} from '../../lz-config/types'

type Props = {
	configList?: validatedBronze[]
}
const { configList } = defineProps<Props>()

const gotoSilverConfig = () => {
	router.replace('/home')
}

</script>
<template>
	<div class="container">
		<h2>Silver</h2>
		<div class="grid-wrap" v-if="configList?.length">
			<button
				class="banner"
				v-for="config in configList"
				:key="config.fileId"
				@click="gotoSilverConfig()"
			>
				<div class="banner-wrap">
					<div>{{ config.fileName }}</div>
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
.invalid-tag {
	background: #f00;
	border-radius: 50%;
	width: 10px;
	height: 10px;
}
.valid-tag {
	background: rgb(0, 255, 0);
	border-radius: 50%;
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
