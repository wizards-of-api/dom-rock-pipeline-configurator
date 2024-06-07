<script setup lang="ts">
import { ref } from 'vue'
import DonutChart from '@/components/DonutChart.vue'

const chartData = ref({
	labels: ['Casa bahia', 'Magalu', 'AluminioLTDA', 'MedicalAlfa', 'WilsonStore'],
	values: [2, 3, 5, 7, 10],
	colors: ['#343838', '#005f6b', '#008c9e', '#00b4cc', '#00dffc'],
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