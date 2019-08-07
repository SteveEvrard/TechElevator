<template>
   <div id="check">
  <a href="#" :class="{ checkIn: isActive }" @click="isActive = !isActive">Click me</a>
</div>
</template>

<script>

import SingleEvent from "../components/SingleEvent.vue";
export default {

}
</script>

<style>

</style>
