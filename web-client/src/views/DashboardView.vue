<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import {ref, watch} from 'vue'
import DonutChart from '@/components/DonutChart.vue'
import {generateColors } from '@/utils/colorUtils'

interface ChartData {
  labels: string[];
  values: number[];
  colors: string[];
  title: string;
}

const labels = ['Xé', 'Zé', 'Ré', 'Dó', 'Só', 'paulinho']

const labelsLength = labels.length

const chartData = ref<ChartData>({
	labels: labels,
	values: Array(labelsLength).fill(1),
	colors: generateColors(labelsLength),
	title: 'Usuário por empresa',
})

console.log(chartData.value.colors);

watch(
	() => chartData.value.labels,
	(newLabels) => {
		chartData.value.colors = generateColors(newLabels.length)
	},
	{ immediate: true })

</script>
<template>
	<AppHeader>
	</AppHeader>
  	<div id="donutChartContainer">
		<DonutChart :chartData="chartData" class="custom-donut-chart"></DonutChart>
	</div>
</template>

<style scoped lang="scss">
main {
	margin-top:10%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content:center;
	gap:10px;
}

.nav {
	display: flex;
	align-items: flex-end;
	justify-content: center;
	gap: 30px;
}

#donutChartContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 400px;
  margin-top: 80px;
}

.custom-donut-chart {
  width: 50%;
}

</style>
