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
		logDate: "10/02/1997",
		logUSer: "Claudio",
		logInterprise: "Fatec",
		logAction: "DELETE",
	},
	{
		logId: 2,
		logDate: "10/02/1997",
		logUSer: "Fred",
		logInterprise: "Americanas",
		logAction: "UPDATE",
	},
	{
		logId: 3,
		logDate: "10/02/1997",
		logUSer: "FLAVIO",
		logInterprise: "LOL",
		logAction: "DELETE",
	},
	{
		logId: 4,
		logDate: "10/02/1997",
		logUSer: "Aloisio",
		logInterprise: "Mc donald's",
		logAction: "DELETE",
	},
]
</script>

<template>
	<div id="sandbox">
		<TableComponent :log-list="listLog"></TableComponent>
    </div>
	<!-- 
		<h1>Foo</h1>
		<p>
			Lorem Ipsum
			<strong>STRONG</strong>
			<span class="section-title">Section Title</span>
		</p>
		<p>
			<span class="section-title">Landing Zone</span>
		</p>
		
		<DRButton :click-behavior="toggleHello">ABC</DRButton>
		<h1 v-if="showHello">Hello World!</h1>
		<h1 v-for="number in list" :key="number">
			{{ number }}
		</h1>
		<DRTextInput title="Nome da Configuração"></DRTextInput>
		<DRDropDown title="Nome Dropdown" :option-list="['Hello', 'World']"></DRDropDown>
	</div>
	<div>
		<Load></Load>
	</div> -->
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
  
  .chart-container {
	width: 100%;
	max-width: 800px;
	height: 400px;
	margin: 10px;
  }
  </style>
  
