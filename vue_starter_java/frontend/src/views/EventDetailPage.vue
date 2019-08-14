<template>
  <div class="event">
    <div class="flex-box">
      <single-event-logged-in id="this-event" v-bind:event="event"></single-event-logged-in>
      <check-in v-if="adminCheckInCheck()" @checked="saveUserAndEvent"></check-in>
      <check-in v-if="userCanCheckIn()" @checked="saveUserAndEvent"></check-in>
      {{user.username}}{{user.role}}
    </div>
    <!-- v-if="hasCheckedIn" -->
    <div id="to-next-page" class="select-box" v-on:click="passEventToRate(event.eventId)">Rate Event</div>
    <!-- v-if="isAdmin" -->
    <div
      class="select-box"
      id="to-next-page"
      v-on:click="passEventToDisplay(event.eventId)"
    >View Ratings</div>
  </div>
</template>

<script>
import SingleEventLoggedIn from "@/components/Events/SingleEventLoggedIn.vue";
import CheckIn from "../components/CheckIn.vue";
import SelectBox from "@/components/Formatting/SelectBox.vue";
import auth from "../auth";

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
      event: {
        eventId: Number,
        title: String,
        imgUrl: String,
        date: new Date(),
        time: String,
        eventDescription: String,
        isPrivate: Boolean,
        isBlindTasting: Boolean
        // tastingWhiskeys: Array
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
    this.getUser;
  },
  methods: {
    getEventDetails() {
      fetch(this.API_URL + this.eventId, {
        method: "GET",
        headers: {
          "Access-Control-Allow-Origin": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonEvent => {
          this.event = jsonEvent;
        })

        .catch(err => console.error(err));
    },
    saveUserAndEvent() {
      fetch(this.Checkin_API_URL + this.event.eventId + "/" + user.userId, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
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
      fetch(this.userDetailURL + this.userId, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
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
    },
    checkRole() {
      if (this.user.role.equals("admin")) {
        this.isAdmin = true;
      }
    },
    adminCheckInCheck() {
      if (this.isAdmin && !this.adminHasCheckedIn) {
        return true;
      } else {
        return false;
      }
    },
    userCanCheckIn() {
      if (!this.isAdmin && this.adminHasCheckedIn) {
        return true;
      } else {
        return false;
      }
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


