<script setup lang="ts">
import AppHeader from '@/components/AppHeader.vue'
import { ref } from 'vue'
import PieChart from '@/components/PieChart.vue'
import DonutChart from '@/components/DonutChart.vue'

const pieChartConfigList = ref([
	{ label: '', value: 10, color: '#fa2e59' },
	{ label: 'Categoria B', value: 20, color: '#ff703f' },
	{ label: 'Categoria C', value: 30, color: '#f7bc05' },
	{ label: 'Categoria D', value: 40, color: '#ecf6bb' },
])

const donutChartConfigList = ref([
	{ label: 'Paulo', value: 1, color: 'pink' },
	{ label: 'Julio', value: 1, color: 'magenta' },
	{ label: 'Pedro', value: 1, color: 'lightblue' },
	{ label: 'Andre', value: 1, color: 'gray' },
	{ label: 'Cauê', value: 1, color: 'red' },
])

const pieChartTitle = ref('Distribuição por Categoria')
const donutChartTitle = ref('Usuário por Empresa')
const fontSize = ref(22)  // Definindo o tamanho da fonte
</script>

<template>
	<AppHeader />
	<main>
	  <div id="chartsContainer">
		<div class="chart-wrapper">
		  <PieChart :title="pieChartTitle" :configList="pieChartConfigList" :fontSize="fontSize" class="custom-pie-chart" />
		</div>
		<div class="chart-wrapper">
		  <DonutChart :title="donutChartTitle" :configList="donutChartConfigList" :fontSize="fontSize" class="custom-donut-chart" />
		</div>
	  </div>
	</main>
</template>

<style scoped lang="scss">
main {
  margin-top: 0%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

.nav {
  display: flex;
  align-items: flex-end;
  justify-content: center;
  gap: 40px;
}

#chartsContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row; /* Alinha os gráficos lado a lado */
  width: 400%;
  height: 400%;
  margin-top: 100px;
  gap: 500px; /* Adiciona espaçamento entre os gráficos */
}

.chart-wrapper {
  width: 200%;
  max-width: 500px; /* Ajusta a largura máxima dos gráficos */
  margin: 1px 0;
}

.custom-pie-chart,
.custom-donut-chart {
  width: 400%;
}
</style>
