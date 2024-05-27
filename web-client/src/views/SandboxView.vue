<script setup lang="ts">
import DRButton from '@/components/DRButton.vue'
import DRTextInput from '@/components/DRTextInput.vue'
import DRDropDown from '@/components/DRDropDown.vue'
import DRCheckBox from '@/components/DRCheckBox.vue'
import Load from '@/components/Load.vue'

import { ref } from 'vue'
/*
This view is suposed to test components in Dev mode
No route should lead to this view
*/

// TODO Remove this view on production

const list = [0, 1, 2, 3]
let showHello = ref(false)

const toggleHello = () => {
	console.log('Hello World!')
	showHello.value = !showHello.value
}
</script>

<template>
	<div id="sandbox">
		<h1>Foo</h1>
		<p>
			Lorem Ipsum
			<strong>STRONG</strong>
			<span class="section-title">Section Title</span>
		</p>
		<p>
			<span class="section-title">Landing Zone</span>
		</p>
		
		<DRButton :click-behavior="toggleHello">ABC</DRButton>
		<h1 v-if="showHello">Hello World!</h1>
		<h1 v-for="number in list" :key="number">
			{{ number }}
		</h1>
		<DRTextInput title="Nome da Configuração"></DRTextInput>
		<DRDropDown title="Nome Dropdown" :option-list="['Hello', 'World']"></DRDropDown>
	</div>
	<div>
		<Load></Load>
	</div>
</template>

<style lang="scss">
#sandbox {
	flex-grow: 1;
	padding: 0 128px;
	border: 1px solid var(--color-text);
	border-top: 0;
	border-bottom: 0;
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
}
</style>
