<script setup lang="ts">
type Props = {
	title: string
	size?: 'small'
	isTextArea?: boolean
	customHeight?: number
	defaultValue?: any
}
const { title, size, isTextArea, customHeight } = defineProps<Props>()
</script>
<template>
	<div class="wrapper" :class="size">
		<span>{{ title }}</span>
		<input class="input" type="text" v-if="!isTextArea" :value="defaultValue"/>
		<textarea :style="{height: customHeight + 'rem'}" class="input" v-else></textarea>
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
</style>
