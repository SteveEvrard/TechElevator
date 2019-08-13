<template>
  <div class="check-in-div">
    <label for="checkin">Check In</label>
    <input type="checkbox" id="checkin" name="checkin" @click:="checkUserIn()">
  </div>
</template>

<script>
export default {
  name: "CheckIn",
  methods: {
    checkUserIn() {
      this.checkin = !this.checkin;
      this.$emit(checked);
    }
  }
};
</script>

<style>
.check-in-div {
  background-color: #51284f;
  font-size: 1.5em;
  padding: 10px;
  color: white;
  font-weight: bold;
  width: fit-content;
  border-radius: 5px;
  box-shadow: -2px 10px 18px -4px rgba(0, 0, 0, 0.75);
  margin: 10px;
}

.check-in-div input {
  width: 15px;
  height: 15px;
}

.check-in-div label {
  padding-right: 5px;
}
</style>
