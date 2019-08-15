<!--
<template>

</template>

<script>
export default {
    created() {
        this.$auth.js.dispatch('clearEvents')
        this.$auth.dispatch('destroyToken')
        .then(response => {
            this.$router.push({name: 'home'})
        })
    }
}
</script>
-->