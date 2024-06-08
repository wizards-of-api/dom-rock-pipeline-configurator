<template>
	<div class="chart-container">
	  <Pie :data="chartData" :options="chartOptions"></Pie>
	</div>
  </template>
  
<script setup lang="ts">
import { defineProps, computed } from 'vue'
import { Pie } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement, CategoryScale } from 'chart.js'
import type { ChartOptions } from 'chart.js'
  
ChartJS.register(Title, Tooltip, Legend, ArcElement, CategoryScale)
  
  interface ConfigItem {
	label: string
	value: number
	color: string
  }
  
  interface ChartProps {
	title: string
	configList: ConfigItem[]
  }
  
const props = defineProps<ChartProps>()
  
const chartData = computed(() => ({
	labels: props.configList.map(item => item.label),
	datasets: [
	  {
			data: props.configList.map(item => item.value),
			backgroundColor: props.configList.map(item => item.color),
	  },
	],
}))
  
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
			text: props.title,
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
  
