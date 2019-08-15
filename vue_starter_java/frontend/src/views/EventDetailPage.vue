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
    <div class="event-detail">
      <div class="flex-box">
        <tile-format class="single-event-detail" id="detail-page-tile">
          <div>
            <h2>{{event.title}}</h2>
            <!-- <img src="{{event.imgUrl}}"> -->
            <div id="line-div">
              <h4>When:</h4>
              <p>{{event.date[1]}} / {{event.date[2]}} / {{event.date[0]}} at {{event.time}}</p>
            </div>
            <div id="line-div">
              <h4>Where:</h4>
              <p>{{event.location}}</p>
            </div>
            <div>
              <div id="description">
                <h4>About the Event</h4>
                <p>{{event.eventDescription}}</p>

                <div v-if="hasCheckedIn">
                  <h4 id="table-label">Your Ratings:</h4>
                  <table class="table">
                    <tr>
                      <th>Whiskey</th>
                      <th>Taste</th>
                      <th>Smell</th>
                      <th>Color</th>
                      <th>Finish</th>
                      <th>Price</th>
                      <th>Overall</th>
                    </tr>
                    <tr v-for="rating in whiskeyRatingList" :key="rating">
                      <td>{{rating.whiskey.brand}}</td>
                      <td>{{rating.tasteRating}}</td>
                      <td>{{rating.smellRating}}</td>
                      <td>{{rating.colorRating}}</td>
                      <td>{{rating.finishRating}}</td>
                      <td>{{rating.priceRating}}</td>
                      <td>{{rating.overallRating}}</td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div
            v-if="!isAdmin && adminHasCheckedIn"
            id="to-next-page"
            class="select-box"
            v-on:click="passEventToRate(event.eventId)"
          >Rate Whiskey</div>
          <div
            v-if="isAdmin"
            class="select-box"
            id="to-next-page"
            v-on:click="passEventToDisplay(event.eventId)"
          >View Ratings</div>
        </tile-format>
        <div v-if="!hasCheckedIn && adminHasCheckedIn">
          <div class="check-in-div" v-if="!isAdmin" @click="saveUserAndEvent()">
            <label for="checkin">Check In</label>
            <input type="checkbox" id="checkin" name="checkin" @click:="saveUserAndEvent()">
          </div>
        </div>
        <div class="check-in-div" v-if="isAdmin" @click="saveUserAndEvent()">
          <label for="checkin">Check In</label>
          <input type="checkbox" id="checkin" name="checkin" @click:="saveUserAndEvent()">
        </div>
        <router-link
          v-if="!isAdmin && hasCheckedIn"
          class="event-survey"
          v-bind:to="{ name: 'eventSurveyPage' }"
        >After the event, take the survey!</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import SingleEventLoggedIn from "@/components/Events/SingleEventLoggedIn.vue";
import TileFormat from "@/components/Formatting/TileFormat.vue";
import auth from "@/auth";

export default {
  components: {
    SingleEventLoggedIn,
    TileFormat
  },
  data() {
    return {
      isAdmin: false,
      hasCheckedIn: false,
      adminHasCheckedIn: false,
      API_URL: "http://localhost:8080/AuthenticationApplication/api/event/",
      Checkin_API_URL:
        "http://localhost:8080/AuthenticationApplication/api/event/",
      userDetailURL: "http://localhost:8080/AuthenticationApplication/api/user",
      adminCheckinApiURL:
        "http://localhost:8080/AuthenticationApplication/api/admin/checkin/",
      userCheckinApiURL:
        "http://localhost:8080/AuthenticationApplication/api/admin/checkin/",
      event: {
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
      eventId: null,

      user: {
        role: ""
      }
    };
  },
  created() {
    this.eventId = this.$route.params.eventId;
    this.getEventDetails();
    this.user.role = auth.getUser().rol;
    if (this.user.role == "admin") {
      this.isAdmin = true;
    }
    this.getAdminCheckin();
    this.user.role = auth.getUser().rol;
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
          this.event = jsonEvent;
        })

        .catch(err => console.error(err));
    },
    saveUserAndEvent() {
      fetch(this.Checkin_API_URL + this.event.eventId, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.event)
      }).catch(err => console.error(err));
      this.hasCheckedIn = true;
    },
    getAdminCheckin() {
      fetch(this.adminCheckinApiURL + this.eventId, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        }
      })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonBoolean => {
          this.adminHasCheckedIn = jsonBoolean;
          console.log(jsonBoolean);
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
table {
  border: 1px solid #f1f1f1;
  border-collapse: collapse;
  margin: 2% 3% 1% 3%;
}
td,
th {
  padding: 6px;
  border: 1px solid #f1f1f1;
}
th {
  background-color: rgba(120, 113, 33, 0.5);
  font-size: 1.3em;
}
.check-in-div {
  background-color: #51284f;
  font-size: 1.5em;
  color: white;
  font-weight: bold;
  width: fit-content;
  height: fit-content;
  border-radius: 5px;
  box-shadow: -2px 10px 18px -4px rgba(0, 0, 0, 0.75);
  margin: 5% 2% 2% 2%;
}

.check-in-div input {
  width: 25px;
  height: 25px;
}

.check-in-div label {
  padding: 5%;
}
.event-detail {
  background-image: url("../assets/img/whiskey-glasses.jpg");
  background-repeat: no-repeat;
  background-size: 120%;
  background-attachment: fixed;
}
.event-survey {
  background-color: rgba(0, 0, 0, 0.7);
  font-size: 3em;
  font-style: italic;
  text-decoration: none;
  color: white;
  margin: 10%;
  padding: 3%;
  border-radius: 5px;
}
.flex-box {
  display: flex;
  justify-content: flex-start;
}
.select-box {
  margin: 2%;
  padding: 2%;
  width: 90%;
  background-color: #2e4d58;
  color: white;
  font-size: 1.2em;
}
.select-box div:hover {
  background-color: #2e4d58;
}
.single-event-detail h4 {
  display: inline-block;
  margin: 10px 0px 5px 0px;
  font-size: 2em;
  font-weight: 500px;
  padding-right: 10px;
  color: black;
}
.single-event-detail p {
  margin: 0px;
  display: inline-block;
  font-size: 1.8em;
}
.single-event-detail h2 {
  text-align: center;
  margin: 20px 0px 40px 0px;
  font-family: "Great Vibes", sans-serif;
  font-size: 4em;
  font-weight: 300px;
}
.single-event-detail {
  padding: 5px;
  background-color: white;
  width: 350px;
  height: 250px;
}

#line-div {
  width: 100%;
  display: block;
  padding: 5px 20px 5px 20px;
}
#this-event {
  width: 50%;
  height: 50%;
}
#to-next-page {
  display: block;
}
#description {
  margin-top: 45px;
  margin-bottom: 10px;
  text-align: center;
  width: 80%;
  padding-right: 10%;
  padding-left: 10%;
}
#description h4 {
  margin-bottom: 10px;
}
#detail-page-tile {
  width: 80%;
  height: fit-content;
  margin-left: 5%;
  margin-right: 5%;
}
#table-label {
  width: fit-content;
  padding-right: 35%;
  padding-left: 35%;
  margin-top: 20px;
}
</style>


