<script setup lang="ts">
type Props = {
	title: String
}
const { title } = defineProps<Props>()

const modelValue = defineModel<boolean>()
const emit = defineEmits(['update'])
const handleChange = (event:any) => {
	modelValue.value = event.target.checked
	emit('update', modelValue.value)
}
</script>
<template>
	<div class="wrapper">
		<span>{{ title }}</span>
		<label>
			<input type="checkbox" v-model="modelValue" @change="handleChange($event)"/>
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
	padding: 2px;

	width: 100%;
	height: 100%;
	border-color: var(--color-text);
	transition:
		border-color 0.2s,
		outline-offset 0.2s ease-out;

	outline-offset: -4px;

	&::after {
		content: '';
		display: block;
		position: relative;

		width: calc(1rem - 6px);
		height: calc(1rem - 6px);
		background: transparent;
		transition: 
			background 0.5s;
	}

	input:focus ~ & {
		padding: 0;
		border-width: 2px;

		outline-offset: 2px;

		outline: 2px solid var(--color-button-neutral);
	}

	input:focus:checked ~ & {
		padding: 0;
		border-width: 2px;
		
		outline: 2px solid  var(--color-text);
	}
	input:focus:checked ~ &::after {
		width: calc(1rem - 8px);
		height: calc(1rem - 8px);
		margin: 2px;
	}

	input:checked ~ & {
		border-color: var(--color-button-neutral);
	}
	input:checked ~ &::after {
		background: var(--color-button-neutral);
	}
}
</style>
