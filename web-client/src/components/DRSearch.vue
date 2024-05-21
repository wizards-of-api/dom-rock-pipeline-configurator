<template>
  <div class="wrap">
    <div class="search">
      <input v-model="handleSearch" @input="updateSearchTerm" type="text" class="searchTerm" placeholder="Pesquisar...">
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, defineEmits } from 'vue'

const emit = defineEmits(['updateSearchTerm'])

const handleSearch = ref('')

const updateSearchTerm = () => {emit('updateSearchTerm', handleSearch.value)
}



</script>

<style scoped lang="scss">
body{
  background: #f2f2f2;
  font-family: 'Open Sans', sans-serif;
}

.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 200px;
  border: 3px solid grey;
  height: 40px;
  border-radius: 5px 5px 5px 5px;
  outline: none; 
  color: #FFFFFF;
}

.searchTerm:focus{
  color: #FFFFFF;
}

.wrap{
  width: 30%;
  position: relative;
  top: 50%;
  left: 15.6%;
  transform: translate(-50%, -50%);
}

</style>
