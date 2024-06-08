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
  margin: 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
}

table caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

table tr {
  background-color: #ddd;
  border: 1px solid #ddd;
  padding: .35em;
  color: #282828;
}

table th,
table td {
  padding: .625em;
  text-align: center;
}

table th {
  font-size: 18px;
  letter-spacing: .1em;
  text-transform: uppercase;
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
    background-color: #282828;
  }
  
  table tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  table td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  table td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  table td:last-child {
    border-bottom: 0;
  }
}

/* general styling */
body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.25;
}
</style>
