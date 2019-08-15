<template>
  <div>
    <div class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="nav-link" v-bind:to="{ name: 'login' }">Login</router-link>
      <router-link v-if="!isAdmin" class="nav-link" v-bind:to="{ name: 'register' }">Register</router-link>
      <router-link v-if="isAdmin" class="nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link
        v-if="isAdmin"
        class="nav-link"
        v-bind:to="{ name: 'resetPassword' }"
      >Reset Password</router-link>
    </div>
    <div class="event">
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
                <!-- v-if="isPast()" -->
                <div>
                  <h4 id="table-label">Your Ratings:</h4>
                  <table class="table" v-if="!isHomePage">
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
              <div v-if="!hasCheckedIn && adminHasCheckedIn">
                <div class="check-in-div" v-if="!isAdmin" @click="saveUserAndEvent()">
                  <label for="checkin">Check In</label>
                  <input type="checkbox" id="checkin" name="checkin" @click:="saveUserAndEvent()">
                </div>
                <div class="check-in-div" v-if="isAdmin" @click="saveUserAndEvent()">
                  <label for="checkin">Check In</label>
                  <input type="checkbox" id="checkin" name="checkin" @click:="saveUserAndEvent()">
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
      user: {
        username: "",
        password: "",
        role: ""
      },
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
    this.getAdminCheckin();
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
    // isPast() {
    //   const datep = $("#datepicker").val();

    //   if (this.event.date.parse(datep) - Date.parse(new Date()) < 0) {
    //     return true;
    //   } else {
    //     return false;
    //   }
    // },
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
  padding: 10px;
  color: white;
  font-weight: bold;
  width: fit-content;
  border-radius: 5px;
  box-shadow: -2px 10px 18px -4px rgba(0, 0, 0, 0.75);
  margin: 5% 0% 2% 2%;
}

.check-in-div input {
  width: 15px;
  height: 15px;
}

.check-in-div label {
  padding-right: 5px;
}

.flex-box {
  display: flex;
  justify-content: flex-start;
}
.select-box {
  margin: 5px;
  width: fit-content;
}
.single-event-detail h4 {
  margin: 10px 0px 5px 0px;
  font-size: 2em;
  font-weight: 500px;
  padding-right: 10px;
  display: inline-block;
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
  width: 60%;
  height: fit-content;
  margin-left: 15%;
  margin-right: 15%;
}
#table-label {
  width: fit-content;
  padding-right: 35%;
  padding-left: 35%;
  margin-top: 20px;
}
</style>


