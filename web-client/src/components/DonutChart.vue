<script setup lang="ts">
import { defineProps, ref } from 'vue'
import { Doughnut } from 'vue-chartjs'
import {
	Chart as ChartJS,
	Title,
	Tooltip,
	Legend,
	ArcElement,
	CategoryScale,
	type ChartOptions,
} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale)

interface ConfigItem {
	label: string
	value: number
	color: string
}

interface ChartProps {
	title: string
}

const props = defineProps<{
	chartData: ChartDataProps
}>()

const chartData = ref({
	labels: props.chartData.labels,
	datasets: [
		{
			data: props.configList.map((item) => item.value),
			backgroundColor: props.configList.map((item) => item.color),
			hoverOffset: 4,
		},
	],
})

const chartOptions: ChartOptions<'doughnut'> = {
	responsive: true,
	maintainAspectRatio: false,
	cutout: '50%', // Aqui fazemos o gráfico ser um donut
	plugins: {
		legend: {
			labels: {
				color: 'white',
				boxWidth: 100,
			},
			position: 'top',
		},
		title: {
			display: true,
			text: props.title,
			color: 'white',
		},
	},
}
</script>

<template>
	<div class="chart-container">
		<Doughnut :data="chartData" :options="chartOptions"></Doughnut>
	</div>
</template>

<style scoped>
.chart-container {
	width: 100%;
	max-width: 800px;
	height: 400px;
	margin: 0 auto;
}
</style>
