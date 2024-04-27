<script setup lang="ts"></script>

<template>
  <header>
    <img src="../assets/dom-rock-logo.png" />
    <img src="../assets/user-icon.png" height="60px"/>
  </header>
</template>

<style lang="scss" scoped>
img {
  max-width: 154px;
}
header {
  display:flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 2rem;
  background: var(--color-background-soft);
  border-bottom: solid 1px var(--color-separator);
  min-width: 100%;
}
</style>
