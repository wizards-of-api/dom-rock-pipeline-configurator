<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import PieChart from '@/components/PieChart.vue'
import { onMounted, ref } from 'vue'
import DonutChart from '@/components/DonutChart.vue'
import { generateColors } from '@/utils/colorUtils'
import BarChart from '@/components/BarChart.vue'
import DRSearch from '@/components/DRSearch.vue'
import api from '@/JwtToken/token'
import ImportantMessage from '@/components/ImportantMessage.vue'

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
	title: '',
})

const fetchPieChart = async () => {
	try {	
		const response = await api.get(`/company/allConfigsByCompanies`)
		const data = response.data

		const labels = Object.keys(data)
		const values = Object.values(data) as number[]

		pieChartData.value = {
			labels,
			values,
			colors: generateColors(labels.length),
			title: '',
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
	title: '',
})

const fetchChartData = async () => {
	try {
		const response = await api.get(`/company/usersByCompany`)
		const data = response.data

		const labels = Object.keys(data)
		const values = Object.values(data) as number[]

		chartData.value = {
			labels,
			values,
			colors: generateColors(labels.length),
			title: '',
		}
	} catch (e) {
		console.error('Erro buscando data do gráfico', e)
	}
}

interface BarChartData {
	labels: string[]
	values: number[]
	colors: string[]
	label: string
}

let barChartData = ref<BarChartData>({
	labels: [],
	values: [],
	colors: [],
	label: "",
})

const fetchBarChartData = async (labels1: string[], values1: number[]) => {
	try {
		barChartData.value = {
			labels: labels1,
			values: values1,
			colors: generateColors(1),
			label: "Quantidade de configurações",
		}
	} catch (e) {
		console.error('Erro buscando data do gráfico', e)
	}
}

let handleSearch = async (updateSearchTerm: string) => {
	try {
		if (updateSearchTerm.length != 4) {
			let response = await api.get(`/lz-config/count-lzfiles/filter-0`)
			let data = response.data
			let labels1 = Object.keys(data)
			let values1 = Object.values(data) as number[]
			fetchBarChartData(labels1, values1)

			response = await api.get(`/lz-config/count-lzfiles`)
			data = response.data
			labels1 = Object.keys(data)
			values1 = Object.values(data) as number[]
			fetchBarChartData(labels1, values1)
		} else {
			let response = await api.get(`/lz-config/count-lzfiles/filter-0`)
			let data = response.data
			let labels2 = Object.keys(data)
			let values2 = Object.values(data) as number[]

			fetchBarChartData(labels2, values2)

			response = await api.get(`/lz-config/count-lzfiles/filter-${updateSearchTerm}`)
			data = response.data
			labels2 = Object.keys(data)
			values2 = Object.values(data) as number[]

			fetchBarChartData(labels2, values2)
		}
	} catch (e) {
		console.error('Erro buscando data do gráfico', e)
	}
}

onMounted(() => {
	fetchChartData()
	fetchPieChart()
	handleSearch('')
})
</script>

<template>
	<AppHeader />
	<main>
		<div id="chartsContainer">
			<div class="chart-wrapper">
				<div class="graph_title">
					<h2>Configurações por Empresa</h2>
				</div>
				<PieChart
					v-if="pieChartData.labels.length"
					:pieChartData="pieChartData"
					class="custom-pie-chart"
				></PieChart>
			</div>
			<div class="chart-wrapper">
				<div class="graph_title">
					<h2>Usuário por empresa</h2>
				</div>
				<DonutChart
					v-if="chartData.labels.length"
					:chartData="chartData"
					class="custom-donut-chart"
				></DonutChart>
			</div>
			<div class="chart-wrapper">
				<div class="graph_title">
					<h2>Quantidade de configurações</h2>
				</div>
				<DRSearch @updateSearchTerm="handleSearch"> </DRSearch>
				<BarChart
					v-if="barChartData.labels.length"
					:barChartData="barChartData"
					class="custom-bar-chart"
				></BarChart>
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
	width: 50%;
	max-width: 500px;
	margin: 1px 0;
}

.custom-bar-chart,
.custom-pie-chart,
.custom-donut-chart {
	width: 100%;
}

.graph_title{
	display: flex;
	justify-content: center;
}
</style>
