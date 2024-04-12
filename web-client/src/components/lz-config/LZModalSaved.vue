<script setup lang="ts">
import DRButton from '../DRButton.vue'
import router from '@/router'

const gotoListView = () => {
	router.replace('/list-view')
}

</script>

<template>
    <div class="wrapper-model">
        <div class="container-header">
            <h2>Salvo com Sucesso!</h2>
        </div>
        <div class="container-buttons">
            <DRButton button-type="safe" :click-behavior="gotoListView">Continuar</DRButton>
        </div>
    </div>
</template>

<style scoped lang="scss">
.wrapper-model {
    width: 960px;
    background: var(--color-background);
    border-radius: var(--border-radius);
}
.container-header {
    padding: var(--gap) var(--big-gap);
    background: var(--color-background-mute);
    border-bottom: 1px solid var(--color-separator);
    border-radius: var(--border-radius) var(--border-radius) 0 0;
}
.container-buttons {
    padding: var(--big-gap) var(--big-gap);
    display: flex;
    justify-content: flex-end;
}
</style>
