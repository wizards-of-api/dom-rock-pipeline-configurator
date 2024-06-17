<script setup lang="ts">
</script>
<template>
	<div class="container_load">
	  <div class="spinner loading"></div>
  </div>
  
</template>
<style scoped lang="scss">
.spinner.loading {
  padding: 50px;
  text-align: center;
  position: fixed;
  top: 50%;         
  left: 46%;    
  transform: translate(-50%, -50%);
}

.spinner.loading:before {
  content: "";
  height: 150px;
  width: 150px;
  position: absolute;
  border-width: 8px;
  border-style: solid;
  border-color: #222222 #00B1ED #00B1ED;
  border-radius: 100%;
  animation: rotation .7s infinite linear;
}

@keyframes rotation {
  from {
    transform: rotate(0deg);
  } to {
    transform: rotate(359deg);
  }
}

</style>
