<script setup lang="ts">
import DRButton from '../../DRButton.vue'
import DRTextInput from '../../DRTextInput.vue'
import router from '@/router'
import type { LZConfig } from '../../lz-config/types'
import api from '@/JwtToken/token'

const deleteFile = async (fileId: Number) => {
	try {
		await api.delete(`/lz-config/delete/${fileId}`)
		location.reload()
	} catch (error) {
		router.replace('/login')
	}
	
}

type Props = {
    lzConfig: LZConfig
}

const { lzConfig } = defineProps<Props>()

const columnsResume = lzConfig.columns.reduce((string, column) => {
	if (!column.status) return string
	string += `${column.columnName}: ${column.type}\n`
	return string
}, '')

const gotoLZConfig = () => {
	router.replace(`/lz-config/update/${lzConfig.fileId}`)
}

</script>
<template>
        <div class="modal">
            <span style="grid-area: config-name;"> <strong> {{ lzConfig.name }} </strong></span>
            <span style="grid-area: file-name;">Arquivo:  {{ lzConfig.fileName }} </span>
            <span style="grid-area: file-type; text-align: right;">Tipo:  {{ lzConfig.fileExtension }} </span>
            <span style="grid-area: file-origin;">Origem:  {{ lzConfig.fileOrigin }} </span>
            <span style="grid-area: file-frequency;">Frequencia:  {{ lzConfig.frequencyNumber }} {{ lzConfig.frequencyType }} </span>
            <div style="grid-area: columns;">
                <h2>Colunas</h2>
                <DRTextInput title="" :is-text-area="true" :custom-height="15" :disabled="true" :default-value="columnsResume"></DRTextInput>
            </div>
            <div class="button-container" style="grid-area: button;">
                <div>
                    <DRButton :click-behavior="gotoLZConfig" v-bind:disabled="false">Visualizar / Editar</DRButton>
                </div>
                <div>
                    <DRButton button-type="careful" :click-behavior="() => deleteFile(lzConfig.fileId)" v-bind:disabled="false">Remover</DRButton>
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
