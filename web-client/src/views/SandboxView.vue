<script setup lang="ts">
import DomRockButton from '@/components/DomRockButton.vue'
import { ref } from 'vue'
/*
This view is suposed to test components in Dev mode
No route should lead to this view
*/

// TODO Remove this view on production

const list = [0, 1, 2, 3]

const foo = {
	bar: 1,
	baz: 2,
	foz: 3,
}

let showHello = ref(false)
const toggleHello = () => {
	console.log(foo)
	showHello.value = !showHello.value
}
</script>

<template>
  <div id="sandbox">
    <h1>Foo</h1>
    <p>Lorem Ipsum <strong>STRONG</strong> <span class="section-title">Section Title</span></p>
    <p>
      <span class="section-title">Landing Zone</span>
    </p>

    <DomRockButton button-type="safe" :click-behavior="toggleHello">ABC</DomRockButton>
    <h1 v-if="showHello">Hello World!</h1>
    <h1 v-for="number in list" :key="number">
      {{ number }}
    </h1>
  </div>
</template>

<style lang="scss">
#sandbox {
  flex-grow: 1;
}
</style>
