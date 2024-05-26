<script setup lang="ts">
import {ref} from 'vue'
import DRSectionTitle from '../DRSectionTitle.vue'
import SilverColumnRow from './SilverColumnRow.vue'
import type {SilverConfig} from './types'


type Props = {
    baseColumnList: SilverConfig[]| []
}

const { baseColumnList } = defineProps<Props>()
const columnList = baseColumnList.map(baseColumn => baseColumn)
const emit = defineEmits(['update'])
const config = ref<SilverConfig>()



const emitUpdate = (index:any, data:any) => {
	if(columnList){
		const column = columnList[index] 
	    Object.assign(column, data)
		emit('update', columnList)
	}
}


</script>
<template>
	<DRSectionTitle title="De/Para"></DRSectionTitle>
	<div v-for="SilverConfig in baseColumnList" :key="SilverConfig.columnId">
	<SilverColumnRow :base-from-to="SilverConfig"></SilverColumnRow>	

</div>
	<main>
	</main>
</template>
<style scoped lang="scss">
.hash{
	display: flex;
	flex-direction : row;
	align-items: center;
	gap:10px;
}
</style>
