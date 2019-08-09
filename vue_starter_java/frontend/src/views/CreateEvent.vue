<template>
  <form-format>
    <form class="event">
<<<<<<< HEAD
      <h1>Create a New Event</h1><br><br><br>
      
        <h4>Title:<br>
        <input type="text" v-model="userData.title" placeholder="Event Title Here">
    </h4>
=======
      <h1>Create a New Event</h1>
      <form-line>
        <h4>Title:</h4>
        <input type="text" v-model="eventData.title">
      </form-line>
>>>>>>> 4b36853828735f4dd4b65eb09c21e124783a01f8
      <!-- <form-line>
        <h4>URL for the event image:</h4>
        <input type="text" v-model="eventData.eventImageURL">
      </form-line>-->
<<<<<<< HEAD
     
        <h4>Is this a private event?
        <input type="checkbox" v-model="userData.isPrivate">
       {{userData.isPrivateEvent ? "yes" : "no"}}</h4>
      
      
        <h4>Date of Event:<br>
        <datepicker class ="center" format="MM/DD/YYYY" v-model="userData.date" name="uniquename"></datepicker></h4>
      
     
        <h4>Time of Event:<br>
        <vue-timepicker format="hh:mm A" :minute-interval="30" v-model="userData.time"></vue-timepicker></h4>
      
        <h4>Location:<br>
        <input type="text" v-model="userData.location"></h4>
      
        <h4>Additional Information:<br>
        <textarea v-model="userData.eventDescription"></textarea></h4>
     
     
        <h4>Is this a blind tasting?
        <input type="checkbox" v-model="userData.isBlindTasting">
        {{userData.isBlindEvent ? "yes" : "no"}}</h4>
     
      <h4>What whiskeys will be tasted?<br>
      <whiskey-brands-to-select :apiURL="API_URL"></whiskey-brands-to-select></h4>

=======
      <form-line class="checkbox">
        <h4>Is this a private event?</h4>
        <input type="checkbox" v-model="eventData.isPrivate">
        {{eventData.isPrivate ? "yes" : "no"}}
      </form-line>
      <form-line>
        <h4>Date of Event:</h4>
        <input type="date" v-model="eventData.date">
      </form-line>
      <form-line>
        <h4>Time of Event:</h4>
        <!-- <input type="text" v-model="eventData.time"> -->
      </form-line>
      <form-line>
        <h4>Location:</h4>
        <input type="text" v-model="eventData.location">
      </form-line>
      <form-line>
        <h4>Additional Information:</h4>
        <textarea v-model="eventData.eventDescription"></textarea>
      </form-line>
      <form-line class="checkbox">
        <h4>Is this a blind tasting?</h4>
        <input type="checkbox" v-model="eventData.isBlindTasting">
        {{eventData.isBlindTasting ? "yes" : "no"}}
      </form-line>
      <h4>What whiskeys will be tasted?</h4>
      <whiskey-brands-to-select :apiURL="API_URL"></whiskey-brands-to-select>
>>>>>>> 4b36853828735f4dd4b65eb09c21e124783a01f8
      <button type="submit" v-on:click.prevent="saveEvent">Create Event</button>
    </form>
  </form-format>
</template>

<script>
import { en } from "vuejs-datepicker/dist/locale";
import FormFormat from "../components/FormFormat.vue";
import FormLine from "../components/FormLine.vue";
import WhiskeyBrandsToSelect from "../components/WhiskeyBrandsToSelect.vue";

export default {
  components: {
    FormFormat,
    FormLine,
    WhiskeyBrandsToSelect
  },

  data() {
    return {
      en: en,
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/events",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
      eventData: {
        title: "",
        // eventImageURL: "",
        isPrivate: true,
        date: new Date(),
        // time: "",
        location: "",
        eventDescription: "",
        isBlindTasting: true
      }
    };
  },
  methods: {
    saveEvent() {
      fetch(this.apiURLEvent, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          if (response.ok) {
            this.$emit("showReviews");
            this.$router.push("/");
          }
        })
        .catch(err => console.error(err));
    }
  }
};
</script>

<style scoped>
button {
  display: block;
  text-align: center;
  margin: 5%;
}
<<<<<<< HEAD

/* .select-box {
  max-height: max-content;
} */
h1{
  text-align: center;
  background: black;
  color: white;
}
h4{
  
  align-content: center;
  text-align: center;
  align-items: center;
  align-self: center;
  
  
}
.center{
  align-items: center;
}

=======
>>>>>>> 4b36853828735f4dd4b65eb09c21e124783a01f8
</style>




