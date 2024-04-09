<script setup lang="ts">
type Props = {
	title: string
	size?: 'small'
}
const { title, size } = defineProps<Props>()
</script>
<template>
	<div class="wrapper" :class="size">
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
