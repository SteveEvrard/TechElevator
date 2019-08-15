<template>
  <div>
    <div class="admin-nav">
      <router-link class="admin-nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="admin-nav-link" :to="{ name: 'home' }">Logout</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'resetPassword' }">Reset Password</router-link>
    </div>
    <div id="reset-password">
      <form-format class="reset-password">
        <h1 class="h3 mb-3 font-weight-normal">Reset Password</h1>
        <label for="username">Email</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Email"
          v-model="user.username"
          required
          autofocus
        >
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        >
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        >

        <button id="thisbtn" type="submit" @click.prevent="resetPassword()">Reset</button>
      </form-format>
    </div>
  </div>
</template>

<script>
import FormFormat from "@/components/Formatting/FormFormat.vue";
import auth from "@/auth";

export default {
  components: {
    FormFormat
  },

  data() {
    return {
      resetAPI_URL: "http://localhost:8080/AuthenticationApplication/api/reset",
      user: {
        username: "",
        password: ""
      }
    };
  },
  methods: {
    resetPassword() {
      fetch(this.resetAPI_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.user)
      }).catch(err => console.error(err));
    }
  }
};
</script>

<style>
.create {
  text-align: center;
  align-items: center;
  align-self: center;
}

.reset-password {
  margin-top: 20px;
  background: black;
  margin: 0px;
  border: 3px solid #f1f1f1;
  width: 70%;
  align-content: center;
  text-align: center;
}

.reset-password h4 {
  margin: 35px 0px 0px 10px;
}

#reset-password {
  background-image: url("../assets/img/whiskey-glasses.jpg");
  background-repeat: no-repeat;
  background-size: 120%;
  background-attachment: fixed;
  padding: 5%;
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

/* Full-width inputs */
.reset-password > input[type="text"],
textarea,
input {
  width: 95%;
  padding: 12px 20px;
  margin: 8px 0px 0px 0px;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  font-size: 1.3em;
  font-family: "Open Sans", sans-serif;
  color: #75787b;
}

/* Set a style for this button */
#create-event-button {
  background-color: #2e4d58;
  color: white;
  margin: 2% 2.5% 3% 2.5%;
  border: none;
  cursor: pointer;
  width: 95%;
  font-size: 1.2em;
}
#whiskey-brands-select {
  width: 95%;
  margin: 2% 2.5% 0% 2.5%;
  color: black;
  font-size: 1.2em;
}

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}
h4 {
  color: white;
  text-align: center;
  justify-content: center;
}
</style>




