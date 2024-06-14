<script setup lang="ts">
import { onMounted, ref } from 'vue'
import axios from 'axios'
import TableComponent from '@/components/TableComponent.vue'
import AppHeader from '@/components/AppHeader.vue'

const API_URL = 'http://localhost:8080/logs/getall'

const listLog = ref([])

const loadLogs = async () => {
    try {
        const response = await axios.get(API_URL)
        listLog.value = response.data
    } catch (error) {
        console.error('Error fetching logs:', error)
    }
}

onMounted(loadLogs)
</script>

<template>
  <AppHeader />
  <div id="log-view">
    <TableComponent :logList="listLog" />
  </div>
</template>

<style scoped lang="scss">
body {
  font-family: "Open Sans", sans-serif;
  line-height: 1.5;
  color: #333;
  background-color: #ffffff;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

table {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 1em auto;
  padding: 0;
  width: 90%;
  max-width: 1200px;
  table-layout: fixed;
  font-size: 1em;
  background-color: #fff;
}

table caption {
  font-size: 1.5em;
  margin: 0.5em 0 0.75em;
  text-align: left;
  font-weight: bold;
  color: #333;
}

table thead {
  background-color: #f0f0f0;
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
  background-color: #e0e0e0;
  color: #333;
}

table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

table tbody tr:hover {
  background-color: #e9e9e9;
}

table td {
  text-align: left;
  color: #555;
}

@media screen and (max-width: 600px) {
  table {
    border: 0;
    width: 100%;
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
