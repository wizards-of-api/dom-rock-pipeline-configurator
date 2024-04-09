<script setup lang="ts">
type Props = {
	buttonType?: 'careful' | 'safe'
	clickBehavior: (event: Event) => void
}

const { buttonType, clickBehavior } = defineProps<Props>()
</script>

<template>
	<button :class="buttonType" v-on:click="clickBehavior">
		<slot></slot>
	</button>
</template>

<style scoped lang="scss"></style>
