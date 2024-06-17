<script setup lang="ts">
import type { LogViewDTO } from './types'

type Props = {
  logList: LogViewDTO[],
}

const { logList } = defineProps<Props>()
</script>

<template>
  <div class="table-container">
    <table>
      <caption>Tabela de log</caption>
      <thead>
        <tr>
          <th scope="col" style="width: 10%;">ID</th>
          <th scope="col" style="width: 20%;">Data</th>
          <th scope="col" style="width: 20%;">Usuário</th>
          <th scope="col" style="width: 20%;">Empresa</th>
          <th scope="col" style="width: 20%;">Ação</th>
          <th scope="col" style="width: 10%;">Código de Resposta</th>
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
  </div>
</template>

<style scoped lang="scss">
.table-container {
  max-height: 80vh;
  overflow-y: auto;
  margin: 1em;
  padding: 1em;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

table {
  width: 100%;
  border-collapse: collapse;
  font-size: 1em;
  color: #333;
}

table caption {
  font-size: 1.5em;
  margin-bottom: 0.75em;
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
  background-color: #fff;
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

@media screen and (max-width: 600px) {
  .table-container {
    max-height: none;
    overflow: visible;
  }

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
</style>
