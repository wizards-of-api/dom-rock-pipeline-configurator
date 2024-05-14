<template>
  <div class="wrap">
    <div class="search">
      <input v-model="handleSearch" @input="updateSearchTerm" type="text" class="searchTerm" placeholder="Pesquisar...">
      <button type="button" class="searchButton">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-search" viewBox="0 0 16 16" style="vertical-align: 27; margin-left: -10">
          <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.397l3.85 3.85a1 1 0 1 0 1.415-1.414l-3.85-3.85z"/>
          <path fill-rule="evenodd" d="M7 12a5 5 0 1 1 0-10 5 5 0 0 1 0 10zm1-5a1 1 0 0 1-2 0 1 1 0 0 1 2 0z"/>
        </svg>
      </button>
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
  border-radius: 5px 0 0 5px;
  outline: none; 
  color: #FFFFFF;
}

.searchTerm:focus{
  color: #FFFFFF;
}

.searchButton {
  width: 40px;
  height: 40px;
  border: 0px solid grey;
  background: grey;
  text-align: center;
  color: #000;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 50px;
}

.wrap{
  width: 30%;
  position: relative;
  top: 50%;
  left: 15.6%;
  transform: translate(-50%, -50%);
}

</style>
