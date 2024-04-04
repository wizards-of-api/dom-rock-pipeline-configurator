<script setup lang="ts">
/*
This view is suposed to test components in Dev mode
No route should lead to this view
*/

// TODO Remove this view on production
</script>

<template>
    <div id="sandbox">
        <h1>Foo</h1>
        <p>
            Lorem Ipsum <strong>STRONG</strong> <span class="section-title">Section Title</span>
        </p>
        <p>
            <span class="section-title">Landing Zone</span>
        </p>
    </div>
</template>

<style lang="scss">
    #sandbox {
        flex-grow: 1;
    }
</style>