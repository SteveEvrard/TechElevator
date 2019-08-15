
<template>
  <div>
    <div v-if="!isAdmin" class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="nav-link" :to="{ name: 'home' }">Logout</router-link>
    </div>
    <div v-if="isAdmin" class="admin-nav">
      <router-link class="admin-nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="admin-nav-link" :to="{ name: 'home' }">Logout</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'resetPassword' }">Reset Password</router-link>
    </div>
    <div class="logout">
      <div class="logout-image">
        <h1>Thanks for coming!</h1>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.$auth.js.dispatch("clearEvents");
    this.$auth.dispatch("destroyToken");
    this.$router.push({ name: "home" });
  }
};
</script>

<style>
.logout {
  background-image: url("../assets/img/whiskey-glasses.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  height: border;
  background-attachment: fixed;
}
.logout-image h1 {
  color: white;
  background-color: rgba(0, 0, 0, 0.7);
  height: fit-content;
  width: 80%;
  border-radius: 5px;
  padding: 5%;
}
.logout-image {
  padding: 10%;
}
</style>