<template>
	<div class="chart-container">
	  <Bar :data="barChartData" :options="chartOptions"></Bar>
	</div>
  </template>
  
<script setup lang="ts">
import { Bar } from 'vue-chartjs'
import { defineProps, ref } from 'vue'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
import type { ChartOptions } from 'chart.js'
  
// Register necessary components for the Chart
ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)
  
  // Define props type
  interface BarChartData {
	labels: string[]
	values: number[]
	colors: string[]
	label: string
  }
  
// Define the props
const props = defineProps<{ 
	barChartData: BarChartData 
}>()
  
// Set up the chart data based on props
const barChartData = ref({
	type: 'bar',
	labels: props.barChartData.labels,
	datasets: [
	    {
			data: props.barChartData.values,
			backgroundColor: props.barChartData.colors,
			label: props.barChartData.label,
			borderColor: 'rgba(255, 255, 255, 1)',
			borderWidth: 2,
			
	    },
	],

})
  
// Set up the chart options
const chartOptions: ChartOptions<'bar'> = {
	responsive: true,
	maintainAspectRatio: false,
	plugins: {
	  legend: {
			labels: {
		  color: 'white',
			},
			position: 'top',
	  },
	  title: {
			display: true,
			color: 'white',
	  },
	},
	scales: {
	  y: {
			beginAtZero: true,
			ticks: {
		  color: 'white',
			},
	  },
	  x: {
			ticks: {
		  color: 'white',
			},
	  },
	},
}
</script>
  
  <style scoped>
  .chart-container {
	width: 100%;
	max-width: 555px;
	height: 400px;
	margin: 10px;
  }
  </style>
  
