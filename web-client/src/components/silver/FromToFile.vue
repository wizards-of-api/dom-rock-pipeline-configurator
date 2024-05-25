<script setup lang="ts">
import {ref} from 'vue';
import DRSectionTitle from '../DRSectionTitle.vue'
import SilverColumnRow from './SilverColumnRow.vue'
import type { silverFromTo , SilverConfig} from './types'
import axios from 'axios';
import router from '@/router';

type Props = {
    baseColumnList?: SilverConfig []| []
}
const { baseColumnList } = defineProps<Props>()
const columnList = baseColumnList?.map(baseColumn => baseColumn)
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
	<SilverColumnRow :base-from-to="columnList"></SilverColumnRow>	
			<SilverColumnRow 
				v-for="(columnConfig, index) in columnList"
					:base-column-config="columnConfig"
					:key="`${index}${columnConfig}`"
					@update="(column) => {emitUpdate(index, column)}"
				>
			</SilverColumnRow>
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
