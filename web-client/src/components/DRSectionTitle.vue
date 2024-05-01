<script setup lang="ts">
type Props = {
    title: string
}
const { title } = defineProps<Props>()
</script>
<template>
    <div class="wrapper">
        <h2>{{ title }}</h2>
        <hr/>
    </div>
</template>

<style scoped lang="scss">
.wrapper {
    width: 100%;
}
h2 {
    padding: var(--small-gap) 0;
}
hr {
    border: 0;
    border-top: 2px solid var(--color-separator);
}
</style>
