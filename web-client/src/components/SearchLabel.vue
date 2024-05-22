<script setup lang="ts">
import { onMounted } from 'vue'
type Props = {
	size?: 'big'
	isTextArea?: boolean
	customHeight?: number
	disabled?: boolean
	defaultValue?: string
	
}
const modelValue = defineModel<string>({ default: '' })
const emit = defineEmits(['update'])
const { size, isTextArea, customHeight, defaultValue, disabled} = defineProps<Props>()
onMounted(() => {
	if(defaultValue !== undefined) modelValue.value = defaultValue
})
</script>
<template>
	<div class="wrapper" :class="size">
		<input
			class="input"
			:class="disabled ? 'disabled' : ''"
			type="text"
			:disabled="disabled"
			v-model="modelValue"
			@input="emit('update', ($event.target as HTMLInputElement).value)"
			v-if="!isTextArea"
		/>
		<textarea
			:style="{ height: customHeight + 'rem' }"
			class="input"
			:disabled="disabled"
			v-model="modelValue"
			@input="emit('update', ($event.target as HTMLInputElement).value)"
			v-else
		></textarea>
	</div>
</template>
<style scoped lang="scss">
textarea {
	resize: none;
}
.small {
	max-width: 6rem;
}
.wrapper {
	display: flex;
	flex-direction: column;
}
.big {
	max-width: 30rem;
}
</style>
