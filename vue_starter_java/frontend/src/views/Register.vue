<template>
  <form-format>
    <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div
          class="alert alert-danger"
          role="alert"
          v-if="registrationErrors"
        >There were problems registering this user.</div>
        <label for="username" class="sr-only">Email</label>
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
        <button id = "thisbtn" class="btn btn-lg btn-primary btn-block" type="submit">Create Account</button>
        <router-link :to="{ name: 'login' }">
          <button id = "thisbtn"> Have an account?</button>
        </router-link>
      </form>
    </div>
  </form-format>
</template>

<script>
import FormFormat from "../components/Formatting/FormFormat.vue";

export default {
  components: {
    FormFormat
  },
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user"
      },
      registrationErrors: false
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: "POST",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "application/json"
        },
        body: JSON.stringify(this.user)
      })
        .then(response => {
          if (response.ok) {
            this.$router.push({
              path: "/login",
              query: { registration: "success" }
            });
          } else {
            this.registrationErrors = true;
          }
        })

        .then(err => console.error(err));
    }
  }
};
</script>

<style>
/* Bordered form */
.form-register {
  border: 3px solid #f1f1f1;
  width: 50%;
  align-content: center;
}

#register {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

/* Full-width inputs */
input[type="text"],
input[type="password"] {
  width: 95%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #9B5A1A;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  
  box-sizing: border-box;
  
}

/* Add a hover effect for buttons */
button:hover {
  opacity: 0.8;
}

/* Extra style for the cancel button (red) */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Add padding to containers */
.container {
  padding: 16px;
}

/* The "Forgot password" text */
span.psw {
  float: right;
  padding-top: 16px;
}
/* added white font and centered*/
.sr-only {
  color: white;
  text-align: center;
  justify-content: center;
}
#thisbtn{
  
  width: 95%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid grey;
  box-sizing: border-box;
  font-size:20px
}
</style>
