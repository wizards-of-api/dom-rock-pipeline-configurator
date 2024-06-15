<script setup lang="ts">
import TableComponent from '@/components/TableComponent.vue'
import { ref } from 'vue'
/*
This view is supposed to test components in Dev mode
No route should lead to this view
*/

// TODO Remove this view on production

const listLog = [
	{
		logId: 1,
		logDate: '10/02/1997',
		logUSer: 'Claudio',
		logInterprise: 'Fatec',
		logAction: 'DELETE',
	},
	{
		logId: 2,
		logDate: '10/02/1997',
		logUSer: 'Fred',
		logInterprise: 'Americanas',
		logAction: 'UPDATE',
	},
	{
		logId: 3,
		logDate: '10/02/1997',
		logUSer: 'FLAVIO',
		logInterprise: 'LOL',
		logAction: 'DELETE',
	},
	{
		logId: 4,
		logDate: '10/02/1997',
		logUSer: 'Aloisio',
		logInterprise: "McDonald's",
		logAction: 'DELETE',
	},
]
import DashboardView from './DashboardView.vue'
import DRSearch from '@/components/DRSearch.vue'

</script>

<template>
	<div id="sandbox">
		<DashboardView />
		<div>
			<h1>Selecione o Ano</h1>
	<DRSearch></DRSearch>
		</div>	
	</div>
	<div>

	</div>
</template>

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

#datepicker {
	flex-grow: 1;
	padding: 0 500px;
	background-color: red;
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
