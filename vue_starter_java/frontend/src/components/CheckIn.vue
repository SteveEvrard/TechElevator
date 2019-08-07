<template>
   <div>
  <button @click='toggle()'>Check In</button>
</div>
</template>

<script>

import SingleEvent from "../components/SingleEvent.vue";
export default {
el: '#app',
    data: {
        isChecked: false
    },
    methods:{
        toggle: function(){
            this.isChecked = !this.isChecked
        }
    }
}

</script>

<style>

</style>
