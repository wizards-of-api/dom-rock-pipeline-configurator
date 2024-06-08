<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import { onMounted, ref } from 'vue'
import DonutChart from '@/components/DonutChart.vue'
import { generateColors } from '@/utils/colorUtils'

interface ChartData {
	labels: string[]
	values: number[]
	colors: string[]
	title: string
}

const chartData = ref<ChartData>({
	labels: [],
	values: [],
	colors: [],
	title: 'Usuário por empresa',
})

const fetchChartData = async () => {
	try {
		const response = await fetch(`http://localhost:8080/company/usersByCompany`)
		const data = await response.json()

		const labels = Object.keys(data)
		const values = Object.values(data) as number[]

		chartData.value = {
			labels,
			values,
			colors: generateColors(labels.length),
			title: 'Usuário por empresa',
		}
	} catch (e) {
		console.error('Erro buscando data do gráfico', e)
	}
}

onMounted(() => {
	fetchChartData()
})
</script>

<template>
	<AppHeader> </AppHeader>
	<div id="donutChartContainer">
		<DonutChart
			v-if="chartData.labels.length"
			:chartData="chartData"
			class="custom-donut-chart"
		></DonutChart>
	</div>
</template>

<style scoped lang="scss">
main {
	margin-top: 10%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	gap: 10px;
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
