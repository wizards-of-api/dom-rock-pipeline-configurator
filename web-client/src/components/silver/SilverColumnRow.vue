<script setup lang="ts">
import type { SilverConfig } from './types'
import DRTextInput from '../DRTextInput.vue'
import { onMounted } from 'vue'
import DRButton from '../DRButton.vue'


type Props = {
    basecolumnconfig: SilverConfig|SilverConfig,
}

const { basecolumnconfig } = defineProps<Props>()
const columnId = defineModel<number>('columnId')
const silverId = defineModel<number>('silverId')
const fromC = defineModel<string>('from_c')
const toC = defineModel<string>('toc_c')
const columnName = defineModel<string>('columnName')

const emit = defineEmits(['update'])
const emitUpdate = (valor:any) => {
	emit('update')
}
onMounted(() => {
	columnId.value = basecolumnconfig?.columnId,
	silverId.value = basecolumnconfig?.silverId,
	fromC.value = basecolumnconfig?.from_c
	toC.value = basecolumnconfig?.to_c
})

const clickTest = () => {

}

</script>
<template>
    <div
        class="grid column-config"
        :key="columnId">
        <DRTextInput
            style="grid-area: index"
            title="Número da Coluna"
            default-value='columnId.value'
            v-model="columnId"
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
