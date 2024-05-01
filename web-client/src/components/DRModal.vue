<script setup lang="ts">
import { ref } from 'vue'

const parent = ref<HTMLElement>()

const emit = defineEmits(['clickOut'])
const emitClickOut = (event: Event) => {
	if (parent.value !== (event.target as HTMLElement)) return
	emit('clickOut')
}

type Props = {
    show?: boolean
}
const { show } = defineProps<Props>()
</script>

<template>
    <Transition name="fade">
        <div ref="parent" :on-blur="parent?.focus" class="wrapper-modal" @click="emitClickOut" v-if="show">
            <slot></slot>
        </div>
    </Transition>
</template>

<style scoped lang="scss">
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.fade-enter-to,
.fade.leave-from {
	opacity: 1;
}

.wrapper-modal {
    position: fixed;
    width: 100vw;
    height: 100vh;
    background: rgba(0, 0, 0, 0.7);
    backdrop-filter: blur(2px);
    display: flex;
    justify-content: center;
    align-items: center;

    z-index: 100;
}
</style>
