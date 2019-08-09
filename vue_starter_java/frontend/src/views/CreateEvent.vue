<template>
  <form-format>
    <form class="event">
      <h1>Create a New Event</h1><br><br><br>
      
        <h4>Title:<br>
        <input type="text" v-model="userData.title" placeholder="Event Title Here">
    </h4>
      <!-- <form-line>
        <h4>URL for the event image:</h4>
        <input type="text" v-model="userData.eventImageURL">
      </form-line>-->
     
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

      <button type="submit" v-on:click.prevent="saveEvent">Create Event</button>
    </form>
  </form-format>
</template>

<script>
import Datepicker from "vuejs-datepicker";
import { en } from "vuejs-datepicker/dist/locale";
import FormFormat from "../components/FormFormat.vue";
import VueTimepicker from "vue2-timepicker";
import FormLine from "../components/FormLine.vue";
import WhiskeyBrandsToSelect from "../components/WhiskeyBrandsToSelect.vue";

export default {
  components: {
    Datepicker,
    FormFormat,
    VueTimepicker,
    FormLine,
    WhiskeyBrandsToSelect
  },

  data() {
    return {
      en: en,
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/events",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
      userData: {
        title: "",
        // eventImageURL: "",
        isPrivate: true,
        date: new Date(),
        time: "",
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
        body: JSON.stringify(this.userData)
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

</style>




