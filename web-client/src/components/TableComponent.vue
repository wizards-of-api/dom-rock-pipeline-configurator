<script setup lang="ts">
import type { Log } from './lz-config/types'

type Props = {
	logList: Log[],
}
const { logList} = defineProps<Props>()
</script>
<template>
	<table>
  <caption>Tabela de log</caption>
  <thead>
      <th scope="col">Data</th>
      <th scope="col">Usuário</th>
      <th scope="col">Empresa</th>
      <th scope="col">Ação</th>
  </thead>
  <tbody v-for="log in logList" :key="log.logId">
    <tr>
      <td data-label="Data">{{ log.logDate }}</td>
      <td data-label="User">{{ log.logUSer }}</td>
      <td data-label="enterprise">{{log.logInterprise}}</td>
      <td data-label="action">{{ log.logAction}}</td>
    </tr>
  </tbody>
</table>

</template>
<style scoped lang="scss">
table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 1em 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
  font-size: 1em;
}

table caption {
  font-size: 1.5em;
  margin: 0.5em 0 0.75em;
  text-align: left;
  font-weight: bold;
  color: #333;
}

table thead {
  background-color: #f4f4f4;
}

table th,
table td {
  padding: 0.75em;
  text-align: center;
  border: 1px solid #ddd;
}

table th {
  font-size: 1em;
  letter-spacing: 0.05em;
  text-transform: uppercase;
  background-color: #f8f8f8;
  color: #333;
}

table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

table tbody tr:hover {
  background-color: #f1f1f1;
}

table td {
  text-align: left;
  color: #555;
}

@media screen and (max-width: 600px) {
  table {
    border: 0;
  }

  table caption {
    font-size: 1.3em;
  }

  table thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }

  table tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: 0.625em;
  }

  table td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: 0.8em;
    text-align: right;
    padding: 0.625em;
  }

  table td::before {
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
    color: #333;
  }

  table td:last-child {
    border-bottom: 0;
  }
}

/* General styling */
body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.5;
  color: #333;
  background-color: #f9f9f9;
  margin: 0;
  padding: 0;
}
</style>
