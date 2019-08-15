<template>
  <div>
    <div class="admin-nav">
      <router-link class="admin-nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="admin-nav-link" :to="{ name: 'logout' }">Logout</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'createEvent' }">Create Event</router-link>
      <router-link class="admin-nav-link" v-bind:to="{ name: 'resetPassword' }">Reset Password</router-link>
    </div>
    <div id="create-event">
      <form-format class="event-background">
        <h1>Create a New Event</h1>

        <h4>Title:</h4>

        <input type="text" v-model="eventData.title" placeholder="Event Title">

        <h4>
          Is this a private event?
          <input type="checkbox" v-model="eventData.isPrivate">
          {{eventData.isPrivate ? "yes" : "no"}}
        </h4>

        <h4>Date of Event:</h4>

        <input type="date" v-model="eventData.date">

        <h4>Time of Event:</h4>

        <input type="text" v-model="eventData.time" placeholder="HH:mm am/pm">

        <h4>Location:</h4>

        <input type="text" v-model="eventData.location" placeholder="Address">

        <h4>Additional Information:</h4>

        <textarea v-model="eventData.eventDescription" placeholder="Other Info"></textarea>

        <h4>
          Is this a blind tasting?
          <input type="checkbox" v-model="eventData.isBlindTasting">
          {{eventData.isBlindTasting ? "yes" : "no"}}
        </h4>

        <h4>What whiskeys will be tasted?</h4>

        <whiskey-brands-to-select
          id="whiskey-brands-select"
          :apiURL="API_URL"
          v-model="selectedWhiskeyBrands"
        ></whiskey-brands-to-select>

        <h4>
          Add a new whiskey?
          <input type="checkbox" v-model="addWhiskey">
          {{addWhiskey ? "yes" : "no"}}
        </h4>
        <div class="new-whiskey-brands" v-if="addWhiskey">
          <input type="text" v-model="whiskey1.brand" placeholder="Specific Whiskey name">
          <input type="text" v-model="whiskey2.brand" placeholder="Specific Whiskey name">
          <input type="text" v-model="whiskey3.brand" placeholder="Specific Whiskey name">
          <input type="text" v-model="whiskey4.brand" placeholder="Specific Whiskey name">
          <input type="text" v-model="whiskey5.brand" placeholder="Specific Whiskey name">
        </div>
        <h4>Embed a survey by pasting the html into the text area below.</h4>
        <textarea v-model="survey.question" placeholder="Google Form embed html"></textarea>
        <h4>
          <button
            id="create-event-button"
            type="submit"
            v-on:click.prevent="saveEventAndForm()"
          >Create Event</button>
        </h4>
      </form-format>
    </div>
  </div>
</template>

<script>
import FormFormat from "@/components/Formatting/FormFormat.vue";
import WhiskeyBrandsToSelect from "@/components/Whiskey/WhiskeyBrandsToSelect.vue";
import auth from "../auth";

export default {
  components: {
    FormFormat,
    WhiskeyBrandsToSelect
  },

  data() {
    return {
      isCreated: false,
      selectedWhiskeyBrands: [],
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/events",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
      surveyAPI_URL:
        "http://localhost:8080/AuthenticationApplication/api/save-survey",
      eventData: {
        title: "",
        // eventImageURL: "",
        isPrivate: true,
        date: "",
        time: "",
        location: "",
        eventDescription: "",
        isBlindTasting: true,
        tastingWhiskeys: []
      },
      whiskey: {
        brand: ""
      },
      newWhiskeys: [
        this.whiskey1,
        this.whiskey2,
        this.whiskey3,
        this.whiskey4,
        this.whiskey5
      ],
      whiskey1: {
        brand: ""
      },
      whiskey2: {
        brand: ""
      },
      whiskey3: {
        brand: ""
      },
      whiskey4: {
        brand: ""
      },
      whiskey5: {
        brand: ""
      },
      addWhiskey: false,
      addForm: false,
      googleFormHtml: "",
      survey: {
        eventId: 0,
        question: ""
      }
    };
  },
  methods: {
    saveEvent() {
      this.selectedWhiskeyBrands.forEach(item => {
        this.whiskey.brand = item;
        this.eventData.tastingWhiskeys.push(this.whiskey);
      });
      this.newWhiskeys.forEach(item => {
        if (item != undefined) {
          this.eventData.tastingWhiskeys.push(item);
        }
      });
      fetch(this.apiURLEvent, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          if (response.ok) {
            this.isCreated = true;
          }
        })
        .catch(err => console.error(err));
    },
    saveFormEmbedInfo() {
      this.survey.eventId = this.eventData.eventId;
      let eventId = this.eventData.eventId;
      this.$router.push({ name: "eventDetailPage", params: { eventId } });
      fetch(this.surveyAPI_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          if (response.ok) {
            this.isCreated = true;
          }
        })
        .catch(err => console.error(err));
    },
    saveEventAndForm() {
      this.saveEvent();
      this.saveFormEmbedInfo();
    },
    addWhiskeyToArray() {
      this.eventData.tastingWhiskeys.push(this.whiskey);
      this.addWiskey = false;
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
.add-survey,
.event-background {
  margin-top: 20px;
  background: black;
  margin: 0px;
  border: 3px solid #f1f1f1;
  width: 70%;
  align-content: center;
  text-align: center;
}

.event-background h4 {
  margin: 35px 0px 0px 10px;
}

#create-event {
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
.event-background > input[type="text"],
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




