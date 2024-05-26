<script setup lang="ts">
import type { SilverConfig } from './types'
import DRTextInput from '../DRTextInput.vue'
import { onMounted } from 'vue'
import DRButton from '../DRButton.vue'
import axios from 'axios';

type Props = {
    baseColumnConfig?: SilverConfig,
}

const { baseColumnConfig } = defineProps<Props>()
const emit = defineEmits(['update'])
const columnId = defineModel<number>('columnId')
const silverId = defineModel<number>('silverId')
const from = defineModel<string>('from_c')
const to = defineModel<string>('toc_c')
const columnName = defineModel<string>('columnName')
const emitUpdate = () => {
	emit('update', wrapColumnConfig())
}

onMounted(() => {
	columnId.value = baseColumnConfig?.columnId,
	silverId.value = baseColumnConfig?.silverId,
	from.value = baseColumnConfig?.from
	to.value = baseColumnConfig?.to
	columnName.value = baseColumnConfig?.columnName
})
const wrapColumnConfig = () => ({
	columnId: Number(columnId.value),
	silverId: Number(silverId.value),
	from: from.value,
	to: to.value,
	columnName: columnName.value,
})

const deleteFromTo = async (silverId:number | undefined) => {
	if(silverId){
	    await axios.delete(`http://localhost:8080/silver-config/delete/${silverId}`)
	    location.reload()
	}
}
</script>
<template>
    <div
        class="grid column-config"
        :key=" baseColumnConfig?.silverId">
        <DRTextInput
            style="grid-area: name"
            title="Nome"
            v-model="columnName"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
        <DRTextInput
            style="grid-area: from"
            title="De"
            default-value="de"
            v-model="from"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
        <DRTextInput
            style="grid-area: to"
            title="Para"
            default-value="para"
			v-model="to"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
        <DRButton
          style="grid-area: delete"
          button-type="careful"
          :click-behavior="() => deleteFromTo(baseColumnConfig?.silverId)"
          v-if="baseColumnConfig?.columnId"
        >Remover</DRButton>
    </div>
</template>
<style scoped lang="scss">
.grid {
	display: grid;
	column-gap: var(--big-gap);
	row-gap: var(--gap);
	align-items: last baseline;
	justify-content: flex-start;
}
.column-config {
    width: 100%;
    grid-template-columns: 20% 20% 20% 20% 20%;
	grid-template-rows: min-content 1fr;
	grid-template-areas:
		'name from to delete';
}
</style>
