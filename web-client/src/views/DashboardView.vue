<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import { ref } from 'vue'
import DonutChart from '@/components/DonutChart.vue'

const chartData = ref({
	labels: ['Xé', 'Zé', 'Ré', 'Dó', 'Só'],
	values: [1, 1, 1, 1, 1],
	colors: ['#fa2e59', '#ff703f', '#f7bc05', '#ecf6bb', '#76bcad'],
	title: 'Usuário por empresa',
})

</script>
<template>
	<AppHeader>
	</AppHeader>
  	<div id="donutChartContainer">
		<DonutChart :chartData="chartData" class="custom-donut-chart"></DonutChart>
	</div>
</template>

<style scoped lang="scss">
main {
	margin-top:10%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content:center;
	gap:10px;
}

.nav {
	display: flex;
	align-items: flex-end;
	justify-content: center;
	gap: 30px;
}

#donutChartContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 400px;
  margin-top: 80px;
}

.custom-donut-chart {
  width: 50%;
}

</style>
