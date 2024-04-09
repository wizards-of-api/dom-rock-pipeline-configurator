<script setup lang="ts">
type Props = {
	title: string
}
const { title } = defineProps<Props>()
</script>
<template>
	<div class="wrapper">
		<span>{{ title }}</span>
		<input class="input" type="text" />
	</div>
</template>
<style scoped lang="scss">
.wrapper {
	display: flex;
	flex-direction: column;
}
</style>
