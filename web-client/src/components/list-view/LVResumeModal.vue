<script setup lang="ts">
import DRButton from '../DRButton.vue'
import DRTextInput from '../DRTextInput.vue'
import router from '@/router'

const gotoLZConfig = () => {
	router.replace('/lz-config')
}
</script>

<template>
        <div class="modal">
            <span style="grid-area: config-name;">Americanas Produto</span>
            <span style="grid-area: file-name;">Arquivo: americanas.csv</span>
            <span style="grid-area: file-type; text-align: right;">Tipo: CSV</span>
            <span style="grid-area: file-origin;">Origem: FTP</span>
            <span style="grid-area: file-frequency;">Frequencia: 3 meses</span>
            <div style="grid-area: columns;">
                <h2>Colunas</h2>
                <DRTextInput title="" :is-text-area="true" :custom-height="15" :disabled="true"></DRTextInput>
            </div>
            <div class="button-container" style="grid-area: button;">
                <div>
                    <DRButton :click-behavior="gotoLZConfig">Visualizar / Editar</DRButton>
                </div>
                <div>
                    <DRButton button-type="careful" :click-behavior="() => {}">Remover</DRButton>
                </div>
            </div>
        </div>
</template>
<style scoped lang="scss">

.modal {
    width: 1080px;
    background: var(--color-background);
    padding: var(--padding-size-big);
    display: grid;
    grid-template-columns: max-content 1fr max-content max-content max-content;
    grid-template-rows: min-content min-content 1fr min-content;
    row-gap: var(--big-gap);
    grid-template-areas: 
        "config-name . file-name file-name file-type"
        "file-origin . . . file-frequency"
        "columns columns columns columns columns"
        ". . . button button";

    border-radius: var(--border-radius);
}
.button-container {
    display: flex;
    justify-content: flex-end;
    gap: var(--gap);
}
</style>
