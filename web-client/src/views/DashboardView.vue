<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import PieChart from '@/components/PieChart.vue'
import { onMounted, ref } from 'vue'
import DonutChart from '@/components/DonutChart.vue'
import { generateColors } from '@/utils/colorUtils'

interface PieChartData {
	labels: string[]
	values: number[]
	colors: string[]
	title: string
}

const pieChartData = ref<PieChartData>({
	labels: [],
	values: [], 
	colors: [],
	title: 'Configurações por Empresa',
})

const fetchPieChart = async () => {
	try {	
		const response = await fetch(`http://localhost:8080/company/allConfigsByCompanies`)
		const data = await response.json()

		const labels = Object.keys(data)
		const values = Object.values(data) as number[]

		pieChartData.value = {
			labels,
			values,
			colors: generateColors(labels.length),
			title: 'Configurações por Empresa',
		}
	} catch (e) {
		console.error('Não há informações sobre esse gráfico', e)
	}
}

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
	fetchPieChart()
})
</script>

<template>
	<AppHeader />
	<main>
		<div id="chartsContainer">
			<div class="chart-wrapper">
				<PieChart
					v-if="pieChartData.labels.length"
					:pieChartData="pieChartData"
					class="custom-pie-chart"
				></PieChart>
			</div>
			<div class="chart-wrapper">
				<DonutChart
					v-if="chartData.labels.length"
					:chartData="chartData"
					class="custom-donut-chart"
				></DonutChart>
			</div>
		</div>
	</main>
</template>

<style scoped lang="scss">
main {
	margin-top: 0%;
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
	gap: 40px;
}

#chartsContainer {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: row;
	width: 100%;
	height: 100%;
	margin-top: 100px;
	gap: 50px;
}

.chart-wrapper {
	width: 200%;
	max-width: 500px;
	margin: 1px 0;
}

.custom-pie-chart,
.custom-donut-chart {
	width: 100%;
}
</style>
