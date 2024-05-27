<script setup lang="ts">
type Props = {
	title: string
	optionList?: readonly string[]
	displayOption?: string
}

const modelValue = defineModel()
const emit = defineEmits(['update'])

const { title, optionList, displayOption } = defineProps<Props>()
</script>
<template>
	<div class="wrapper">
		<span>{{ title }}</span>
		<select
			class="input"
			v-model="modelValue"
			@change="emit('update', ($event.target as HTMLSelectElement).value)"
		>
			<option v-if="displayOption" disabled value="">
				{{ displayOption ?? 'Selecione um valor' }}
			</option>
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
	&:focus,
	&:hover {
		background: var(--color-button-careful);
	}
}
</style>
