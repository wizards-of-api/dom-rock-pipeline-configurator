<script setup lang="ts">
type Props = {
	title: string
	optionList: string[]
}
const { title, optionList } = defineProps<Props>()
</script>
<template>
	<div class="wrapper">
		<span>{{ title }}</span>
		<select class="input">
			<option v-for="option in optionList" :key="option">{{ option }}</option>
		</select>
	</div>
</template>
<style scoped lang="scss">
.wrapper {
	display: flex;
	flex-direction: column;
}
option {
	background: var(--color-background-soft);
	&:focus, &:hover {
		background: var(--color-button-careful);
	}
}
</style>
