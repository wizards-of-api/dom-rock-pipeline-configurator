<script setup lang="ts">
type Props = {
	buttonType?: 'careful' | 'safe' | 'home'
	clickBehavior: (event: Event) => void
	disabled: boolean
}

const { buttonType, clickBehavior } = defineProps<Props>()
</script>

<template>
	<button :class="buttonType ?? 'neutral'" v-on:click="clickBehavior" :disabled="disabled ?? false">
		<slot></slot>
	</button>
</template>

<style scoped lang="scss">
</style>
