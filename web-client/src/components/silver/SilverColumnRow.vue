<script setup lang="ts">
import type { silverFromTo } from './types'
import DRTextInput from '../DRTextInput.vue'
import { onMounted } from 'vue'
import DRButton from '../DRButton.vue';


const baseFromTo: silverFromTo = {
    id_silver: 1,
    from_c: 'de',
    to_c: 'para',
}
const columnIndex = defineModel<string>('index')
const fromC = defineModel<string>('fromC')
const toC = defineModel<string>('toC')

const emit = defineEmits(['update'])
const emitUpdate = (valor:any) => {
	setTimeout(() => {
		emit('update')
	}, 1000)
}
onMounted(() => {
	columnIndex.value = String(baseFromTo.id_silver)
	fromC.value = baseFromTo.from_c
	toC.value = baseFromTo.to_c
})

const clickTest = () => {
    
    console.log(columnIndex.value, fromC.value, toC.value)
    console.log("Remover")
}

</script>
<template>
    <div
        class="grid column-config"
        :key="columnIndex">
        <DRTextInput
            style="grid-area: index"
            title="Número da Coluna"
            default-value="String(baseFromTo.id_silver)"
            v-model="columnIndex"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
        <DRTextInput
            style="grid-area: from"
            title="De"
            default-value="de"
            v-model="fromC"
			@update="emitUpdate"
            disabled
        ></DRTextInput>
        <DRTextInput
            style="grid-area: to"
            title="Para"
            default-value="para"
			v-model="toC"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
            <DRButton button-type="careful" :click-behavior="clickTest">Remover</DRButton>
        </div>
</template>
<style scoped lang="scss">
.grid {
	display: grid;
	column-gap: var(--big-gap);
	row-gap: var(--gap);
	align-items: last baseline;
	justify-content: flex-start;
	padding: var(--big-gap) 0;
}
.column-config {
    width: 90%;
    grid-template-columns: 20% 30% 30% 20%;
	grid-template-rows: min-content 2fr;
	grid-template-areas:
		'index from to delete'
		'description description description description';
}
</style>
