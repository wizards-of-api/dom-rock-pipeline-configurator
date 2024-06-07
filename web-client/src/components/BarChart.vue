<template>
	<div class="chart-container">
	  <Bar :data="chartData" :options="chartOptions"></Bar>
	</div>
  </template>
  
<script setup lang="ts">
import { Bar } from 'vue-chartjs'
import { defineProps } from 'vue'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
import type { ChartOptions } from 'chart.js'
  
// Register necessary components for the Chart
ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)
  
  // Define props type
  interface ChartDataProps {
	labels: string[];
	values: number[];
	colors: string[];
	title: string;
  }
  
// Define the props
const props = defineProps<{ chartData: ChartDataProps }>()
  
// Set up the chart data based on props
const chartData = {
	labels: props.chartData.labels,
	datasets: [
	  {
			data: props.chartData.values,
			backgroundColor: props.chartData.colors,
	  },
	],
}
  
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
			text: props.chartData.title,
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
	max-width: 800px;
	height: 400px;
	margin: 0 auto;
  }
  </style>
