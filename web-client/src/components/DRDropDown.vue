<script setup lang="ts">
import { onMounted, ref } from 'vue'


type Props = {
	title: string
	optionList: readonly string[]
	onChange?: (value: string) => void
}

const selectRef = ref<HTMLSelectElement>()
const { title, optionList, onChange } = defineProps<Props>()
const trigger = (e: Event) => {
	if(!onChange) return
	const target = e.target as HTMLSelectElement
	if (target.options.selectedIndex > -1) {
		onChange(target.options[target.options.selectedIndex].value)
	}
}

onMounted(() => {
	if(!selectRef.value) return
	trigger({ target: selectRef.value } as any)
})

</script>
<template>
	<div class="wrapper">
		<span>{{ title }}</span>
		<select ref="selectRef" class="input" v-on:change="trigger" v-on:click="trigger">
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
