<template>
  <div id="create-event">
    <form-format class="event-background">
      <h1>Create a New Event</h1>

      <h4>Title:</h4>
      <br>
      <input type="text" v-model="eventData.title" placeholder="Event Title">
      <br>

      <h4>
        Is this a private event?
        <input type="checkbox" v-model="eventData.isPrivate">
        {{eventData.isPrivate ? "yes" : "no"}}
      </h4>

      <br>
      <h4>Date of Event:</h4>
      <br>
      <input type="date" v-model="eventData.date">
      <br>

      <h4>Time of Event:</h4>
      <br>
      <input type="text" v-model="eventData.time" placeholder="HH:mm am/pm">
      <br>

      <h4>Location:</h4>
      <br>
      <input type="text" v-model="eventData.location" placeholder="Address">
      <br>

      <h4>Additional Information:</h4>
      <br>
      <textarea v-model="eventData.eventDescription" placeholder="Other Info"></textarea>
      <br>

      <h4>
        Is this a blind tasting?
        <input type="checkbox" v-model="eventData.isBlindTasting">
        {{eventData.isBlindTasting ? "yes" : "no"}}
      </h4>
      <br>
      <h4>What whiskeys will be tasted?</h4>
      <br>
      <h4>
        <whiskey-brands-to-select :apiURL="API_URL" v-model="selectedWhiskeyBrands"></whiskey-brands-to-select>
      </h4>
      <br>
      <h4>
        <button type="submit" v-on:click.prevent="saveEvent">Create Event</button>
      </h4>
    </form-format>
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
      selectedWhiskeyBrands: [],
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/events",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
      eventData: {
        title: "",
        // eventImageURL: "",
        isPrivate: true,
        date: new Date(),
        time: "",
        location: "",
        eventDescription: "",
        isBlindTasting: true,
        tastingWhiskeys: []
      },
      whiskey: {
        brand: ""
      }
    };
  },
  methods: {
    saveEvent() {
      this.selectedWhiskeyBrands.forEach(item => {
        this.whiskey.brand = item;
        this.tastingWhiskeys.push(this.whiskey);
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
            let eventId = this.eventData.eventId;
            this.$router.push({ name: "eventPage", params: { eventId } });
          }
        })
        .catch(err => console.error(err));
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

.event-background {
  margin-top: 20px;
  background: black;
  margin: 0px;
  border: 3px solid #f1f1f1;
  width: 50%;
  align-content: center;
  text-align: center;
}

#create-event {
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
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #2e4d58;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
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




