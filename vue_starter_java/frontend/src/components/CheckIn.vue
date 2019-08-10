<template>
  <div>
    <input type="checkbox" id="checkin" name="checkin" @click:="check()">
    <label v-if(checkin) for="checkin">Check In</label>
  </div>
</template>

<script>
export default {
  data() {
    return {
      checkin: false
    };
  },
  methods: {
    check() {
      this.checkin = !this.checkin;
    }
  }
};
</script>

<style>
</style>
