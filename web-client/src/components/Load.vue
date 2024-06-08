<script setup lang="ts">
</script>
<template>
	
	<div class="spinner loading"></div>
  
</template>
<style scoped lang="scss">
.spinner.loading {
  padding: 50px;
  position: relative;
  text-align: center;
}

.spinner.loading:before {
  content: "";
  height: 100px;
  width: 100px;
  margin: -15px auto auto -15px;
  position: absolute;
  top: 50%;
  left: 50%;
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
