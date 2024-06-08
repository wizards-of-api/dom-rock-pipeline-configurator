<script setup lang="ts">
import TableComponent from '@/components/TableComponent.vue'
import { ref } from 'vue'
/*
This view is suposed to test components in Dev mode
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
		logInterprise: "Mc donald's",
		logAction: 'DELETE',
	},
]
import DashboardView from './DashboardView.vue'
</script>

<template>
	<div id="sandbox">
		<DashboardView />
	</div>
	<div>
		<Load></Load>
	</div>
	-->
</template>

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
</style>
