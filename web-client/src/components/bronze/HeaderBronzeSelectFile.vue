<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'

const emit = defineEmits(['update'])

const name = defineModel<string>('name')
const fileOrigin = defineModel<string>('fileOrigin')
const frequencyNumber = defineModel<string>('frequencyNumber')
const frequencyType = defineModel<string>('frequencyType')

</script>
<template>
	<DRSectionTitle title="Arquivo Landing Zone"></DRSectionTitle>
	<div class="grid metadata">
	</div>
</template>

<style scoped lang="scss">
.grid {
	display: grid;
	column-gap: var(--big-gap);
	row-gap: var(--gap);
	align-items: last baseline;
	justify-content: flex-start;
	padding: var(--big-gap) 0;
}
.metadata {
	grid-template-columns: min-content min-content min-content;
	grid-template-rows: min-content min-content;
	grid-template-areas:
		'config-name config-name config-name'
		'origin frequency period'
}
</style>
