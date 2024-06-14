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
