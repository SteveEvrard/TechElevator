<template>
  <div>
    
      <h1>Create a New Event</h1>

      <h4>Title:</h4><br>
      <input type="text" v-model="eventData.title"><br>

    

      <h4>Is this a private event?
      <input type="checkbox" v-model="eventData.isPrivate">
      {{eventData.isPrivate ? "yes" : "no"}}</h4><br>
      <h4>Date of Event:</h4><br>
      <input type="date" v-model="eventData.date"><br>

      <h4>Time of Event:</h4><br>
     <input type="text" v-model="eventData.time"><br>

      <h4>Location:</h4><br>
      <input type="text" v-model="eventData.location"><br>

      <h4>Additional Information:</h4><br>
      <textarea v-model="eventData.eventDescription"></textarea><br>

      <h4>Is this a blind tasting?</h4>
      <input type="checkbox" v-model="eventData.isBlindTasting">
      <h4>{{eventData.isBlindTasting ? "yes" : "no"}}</h4>
      <h4>What whiskeys will be tasted?</h4>
      <whiskey-brands-to-select :apiURL="API_URL"></whiskey-brands-to-select><br>
      <button class ="create" type="submit" v-on:click.prevent="saveEvent">Create Event</button>
   
  </div>
  
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

<style>
button {
  
  text-align: center;
  margin: 5%;
}


h1 {
  text-align: center;
  background: black;
  color: white;
  
}
h4 {
  
  font-size: 20px;
text-align: center;
 
}
div{
  text-align: center;
  align-items: center;
  align-self: center;
  background: #f1f1f1;
 
  
}
.create{
 text-align: center;
  align-items: center;
  align-self: center;
}

</style>




