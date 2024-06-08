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

interface ChartDataProps {
	labels: string[]
	values: number[]
	colors: string[]
	title: string
}

const props = defineProps<{
	chartData: ChartDataProps
}>()

const chartData = ref({
	labels: props.chartData.labels,
	datasets: [
		{
			data: props.chartData.values,
			backgroundColor: props.chartData.colors,
		},
	],
})

const chartOptions: ChartOptions<'doughnut'> = {
	responsive: true,
	maintainAspectRatio: false,
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
			text: props.chartData.title,
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
