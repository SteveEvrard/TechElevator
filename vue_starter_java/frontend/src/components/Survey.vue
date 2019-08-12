<template>
  <div class="survey">
    <h1 class="whiskeyName">{{title}}</h1>
    <div class="questions">
      <single-whiskey-rating></single-whiskey-rating>
    </div>
  </div>
</template>

<script>
import SingleEvent from "@/components/Events/SingleEvent";
import SingleWhiskeyRating from "@/components/Whiskey/SingleWhiskeyRating";

export default {};
</script>

<style>
</style>