<template>
    <div class = "event">
<h1 class = "event"> {{SingleEvent}}</h1>
<h2> {{CheckIn}}</h2>
  
    </div>
</template>

<script>
import SingleEvent from "../components/SingleEvent.vue";
import CheckIn from "../components/CheckIn.vue"
export default {
    components:{
        SingleEvent,
        CheckIn
    }


}
</script>

<style>

</style>
