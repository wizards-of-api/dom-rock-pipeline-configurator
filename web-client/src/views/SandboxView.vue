<script setup lang="ts">
import { ref } from 'vue'
import DonutChart from '@/components/DonutChart.vue'

const chartData = ref({
	labels: ['Casa bahia', 'Magalu', 'AluminioLTDA', 'MedicalAlfa', 'WilsonStore'],
	values: [1, 1, 1, 1, 1],
	colors: ['#fa2e59', '#ff703f', '#f7bc05', '#ecf6bb', '#76bcad'],
	title: 'Configurações por empresa',
})
</script>

<template>
	<div id="sandbox">
		<DonutChart :chartData="chartData"></DonutChart>
	</div>
</template>

<style lang="scss">

</style>
