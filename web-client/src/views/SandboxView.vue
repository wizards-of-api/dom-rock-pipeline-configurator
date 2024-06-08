<template>
	<div id="sandbox">
	  <BarChart :chartData="chartData" />
	</div>
  </template>
  
<script setup lang="ts">
import BarChart from '@/components/BarChart.vue'
import { ref } from 'vue'
  
const chartData = ref({
	labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
	datasets: [
	  {
			label: 'configurações criadas', // Defina o label aqui
			data: [5, 7, 8, 9, 7, 9, 9],
			backgroundColor: [
		  'rgba(54, 162, 235, 0.2)',
			],
			borderColor: [
		  'rgba(54, 162, 235, 1)',
			],
			borderWidth: 1,
	  },
	],
})
</script>
  
  <style lang="scss">
  #sandbox {
	flex-grow: 1;
	padding: 0 500px;
	border: 1px solid var(--color-text);
	border-top: 0;
	border-bottom: 0;
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
  }
  
  .chart-container {
	width: 100%;
	max-width: 800px;
	height: 400px;
	margin: 10px;
  }
  </style>
  
