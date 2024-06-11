<template>
	<div class="chart-container">
	  <Pie :data="pieChartData" :options="chartOptions"></Pie>
	</div>
  </template>
  
<script setup lang="ts">
import { defineProps, ref } from 'vue'
import { Pie } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement, CategoryScale } from 'chart.js'
import type { ChartOptions } from 'chart.js'
  
ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale)
  
interface ConfigItem {
	labels: string[]
	values: number[]
	colors: string[]
	title: string
}
  
const propsConfigItem = defineProps<{
	pieChartData: ConfigItem
}>()
  
const pieChartData = ref({
	labels: propsConfigItem.pieChartData.labels,
	datasets: [
	    {
			data: propsConfigItem.pieChartData.values,
			backgroundColor: propsConfigItem.pieChartData.colors,
	    },
	],
})
  
const chartOptions: ChartOptions<'pie'> = {
	responsive: true,
	maintainAspectRatio: false,
	plugins: {
	  	legend: {
			labels: {
				color: 'white',
				boxWidth: 50,
			},
			position: 'top',
		},
	  	title: {
			display: true,
			text: propsConfigItem.pieChartData.title,
			color: 'white',
	  	},
	},
}
</script>
  
<style scoped>
.chart-container {
	width: 100%;
	max-width: 800px;
	height: 400px;
	margin: 0 auto;
}
</style>
