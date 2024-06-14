<script setup lang="ts">
import type { LogViewDTO } from './types'
type Props = {
  logList: LogViewDTO[],
}
const { logList } = defineProps<Props>()
</script>

<template>
  <table>
    <caption>Tabela de log</caption>
    <thead>
      <tr>
        <th scope="col">ID</th>
        <th scope="col">Data</th>
        <th scope="col">Usuário</th>
        <th scope="col">Empresa</th>
        <th scope="col">Ação</th>
        <th scope="col">Código de Resposta</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="log in logList" :key="log.id">
        <td data-label="ID">{{ log.id }}</td>
        <td data-label="Data">{{ log.logDateTime }}</td>
        <td data-label="Usuário">{{ log.userName }}</td>
        <td data-label="Empresa">{{ log.companyName }}</td>
        <td data-label="Ação">{{ log.action }}</td>
        <td data-label="Código de Resposta">{{ log.responseCode }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped lang="scss">
table {
  border: 1px solid #ccc;
	@@ -42,7 +49,7 @@ table caption {
  margin: 0.5em 0 0.75em;
  text-align: left;
  font-weight: bold;
  color: #ddd;
}
table thead {
	@@ -123,8 +130,6 @@ table td {
    border-bottom: 0;
  }
}
body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.5;
  color: #333;
  background-color: #f9f9f9;
  margin: 0;
  padding: 0;
}
</style>