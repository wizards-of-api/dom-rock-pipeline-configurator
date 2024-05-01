<script setup lang="ts">
import DRSectionTitle from '../DRSectionTitle.vue'

const emit = defineEmits(['update'])

const name = defineModel<string>('name')
const fileOrigin = defineModel<string>('fileOrigin')
const frequencyNumber = defineModel<string>('frequencyNumber')
const frequencyType = defineModel<string>('frequencyType')

</script>
<template>
	<div class="container">
		<DRSectionTitle title="Validação e Hash"></DRSectionTitle>
		<main>
			<div class="grid metadata">
			</div>
		</main>
	</div>	
</template>

<style scoped lang="scss">

main{
	width:100vh;
	margin-top:100%;
}
</style>
