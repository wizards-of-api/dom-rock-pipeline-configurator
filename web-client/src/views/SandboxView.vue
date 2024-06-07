<template>
	<div id="sandbox">
		<BarChart :chartData="chartData" />
	</div>
  </template>
  
<script setup lang="ts">
import BarChart from '@/components/BarChart.vue'
  
import { ref } from 'vue'
/*
  This view is supposed to test components in Dev mode
  No route should lead to this view
  */
  
// TODO Remove this view on production
  
const list = [0, 1, 2, 3]
let showHello = ref(false)
  
const toggleHello = () => {
	console.log('Hello World!')
	showHello.value = !showHello.value
}
  
const chartData = ref({
	labels: ['January', 'February', 'March', 'April', 'May', 'June'],
	values: [65, 59, 80, 81, 56, 55],
	colors: [
	  'rgba(255, 99, 132, 0.2)',
	  'rgba(54, 162, 235, 0.2)',
	  'rgba(255, 206, 86, 0.2)',
	  'rgba(75, 192, 192, 0.2)',
	  'rgba(153, 102, 255, 0.2)',
	  'rgba(255, 159, 64, 0.2)',
	],
	title: 'Monthly Sales',
})
</script>
  
  <style lang="scss">
  #sandbox {
	flex-grow: 1;
	padding: 0 128px;
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
	margin: 20px auto; /* Ajuste a margem conforme necessário */
  }
  </style>
  
