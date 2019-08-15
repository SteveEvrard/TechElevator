<template>
  <div>
    <div class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-if="isAdmin" class="nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'login' }">Login</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'register' }">Register</router-link>
    </div>
    <div class="event">
      <div class="flex-box">
        <keep-alive>
          <single-event-logged-in id="this-event" v-bind:event="eventForDetail"></single-event-logged-in>
        </keep-alive>
        <check-in v-if="!isAdmin" @checked="saveUserAndEvent"></check-in>
        <check-in v-if="isAdmin" @checked="saveUserAndEvent"></check-in>
        {{user.username}}{{user.role}}
      </div>
      <!-- v-if="hasCheckedIn" -->
      <div
        v-if="!isAdmin"
        id="to-next-page"
        class="select-box"
        v-on:click="passEventToRate(eventForDetail.eventId)"
      >Rate Whiskey</div>
      <!-- v-if="isAdmin" -->
      <div
        v-if="isAdmin"
        class="select-box"
        id="to-next-page"
        v-on:click="passEventToDisplay(eventForDetail.eventId)"
      >View Ratings</div>
    </div>
  </div>
</template>

<script>
import SingleEventLoggedIn from "@/components/Events/SingleEventLoggedIn.vue";
import CheckIn from "../components/CheckIn.vue";
import SelectBox from "@/components/Formatting/SelectBox.vue";
import auth from "@/auth";

export default {
  components: {
    SingleEventLoggedIn,
    CheckIn
  },
  data() {
    return {
      user: {
        username: "",
        password: "",
        role: ""
      },
      hasCheckedIn: false,
      adminHasCheckedIn: false,
      API_URL: "http://localhost:8080/AuthenticationApplication/api/event/",
      Checkin_API_URL:
        "http://localhost:8080/AuthenticationApplication/api/event/",
      userDetailURL: "http://localhost:8080/AuthenticationApplication/api/user",
      eventForDetail: {
        eventId: null,
        title: "",
        // imgUrl: "",
        date: new Date(),
        time: "",
        eventDescription: "",
        isPrivate: true,
        isBlindTasting: false,
        tastingWhiskeys: []
      },
      isAdmin: false,
      eventId: null,
      user: {
        username: "",
        password: "",
        role: "",
        id: null
      }
    };
  },
  created() {
    this.eventId = this.$route.params.eventId;
    this.getEventDetails();
    this.getUser();
  },
  methods: {
    getEventDetails() {
      fetch(this.API_URL + this.eventId, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        }
      })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonEvent => {
          this.eventForDetail = jsonEvent;
        })

        .catch(err => console.error(err));
    },
    saveUserAndEvent() {
      fetch(
        this.Checkin_API_URL + this.eventForDetail.eventId + "/" + user.userId,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + auth.getToken()
          },
          body: JSON.stringify(this.eventForDetail)
        }
      )
        .then(response => {
          if (response.ok) {
            if (this.isAdmin) {
              this.adminHasCheckedIn = true;
            } else {
              this.hasCheckedIn = true;
            }
          }
        })
        .catch(err => console.error(err));
    },
    getUser() {
      fetch(this.userDetailURL, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        }
      })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonUser => {
          this.user = jsonUser;
          if (this.user.role == "admin") {
            this.isAdmin = true;
          }
        })
        .catch(err => console.error(err));
    },
    passEventToRate(eventId) {
      this.$router.push({ name: "rateWhiskey", params: { eventId } });
    },
    passEventToDisplay(eventId) {
      this.$router.push({ name: "ratingResults", params: { eventId } });
    }
  }
};
</script>

<style>
.flex-box {
  display: flex;
  justify-content: flex-start;
}
.select-box {
  margin: 5px;
  width: fit-content;
}
#this-event {
  width: 50%;
  height: 50%;
}
#to-next-page {
  display: block;
}
</style>


