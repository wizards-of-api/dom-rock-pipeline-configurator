<script setup lang="ts">
type Props = {
	title: String
}
const { title } = defineProps<Props>()

const modelValue = defineModel()
const emit = defineEmits(['update'])
</script>
<template>
	<div class="wrapper">
		<span>{{ title }}</span>
		<label>
			<input class="input" type="checkbox"  v-model="modelValue" @input="emit('update', ($event.target as HTMLInputElement).checked)"/>
			<span class="checkmark"> </span>
		</label>
	</div>
</template>
<style scoped lang="scss">
.wrapper {
	display: flex;
	align-items: center;
	column-gap: var(--gap);
}
.small {
	max-width: 5rem;
}
input {
	width: 0;
	height: 0;
}
label {
	position: relative;
	min-width: 1rem;
	height: 1rem;
}
.checkmark {
	position: absolute;
	border: 1px solid;
	width: 100%;
	height: 100%;
	border-color: var(--color-text);
	transition: border-color 0.2s;
	&::after {
		content: '';
		display: block;
		position: relative;
		top: 2px;
		left: 2px;

		width: calc(1rem - 6px);
		height: calc(1rem - 6px);
		background: transparent;
		transition: background 0.2s;
	}
	input:checked ~ & {
		border-color: var(--color-button-neutral);
	}
	input:checked ~ &::after {
		background: var(--color-button-neutral);
	}
}
</style>
