<script setup lang="ts">
import DRTextInput from '../DRTextInput.vue'
import { onMounted } from 'vue'
import DRButton from '../DRButton.vue'
import api from '@/JwtToken/token'
import router from '@/router'
import type { EnterpriseModel } from '../silver/types'

type Props = {
    column?: EnterpriseModel,
}

const { column } = defineProps<Props>()
const emit = defineEmits(['update'])
const fantasyName = defineModel<String>('fantasyName')
const cnpj = defineModel<String>('cnpj')
const emitUpdate = () => {
	emit('update', wrapColumnConfig())
}

onMounted(() => {
	fantasyName.value = column?.fantasyName,
	cnpj.value = column?.cnpj
})
const wrapColumnConfig = () => ({
	fantasyName: fantasyName?.value,
	cnpj: cnpj?.value,
})

const deleteFromTo = async (silverId:String | undefined) => {
	if(silverId){
		try {
			await api.delete(`/silver-config/delete/${silverId}`)
	    location.reload()
		} catch (error) {
			router.replace('/login')
		}
	}
}
</script>
<template>
    <div
        class="grid column-config"
        :key=" column?.cnpj">
        <DRSectionTitle title="Empresas:"></DRSectionTitle>
        <DRTextInput
            style="grid-area: name; width: 410px;"
            title="Nome"
            v-model="fantasyName"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
        <DRTextInput
            style="grid-area: from; width: 410px;"
            title="Cnpj"
            v-model="cnpj"
            @update="emitUpdate"
            disabled
        ></DRTextInput>
        <DRButton
          v-bind:disabled="false"
          style="width: 150px;"
          button-type="careful"
          :click-behavior="() => deleteFromTo(column?.cnpj)"
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
    margin-top: 5%;
    width: 100%;
    grid-template-columns: 28% 28% 20% 20% 20%;
	grid-template-rows: min-content 1fr;
	grid-template-areas:
		'name from to delete';
}
</style>
