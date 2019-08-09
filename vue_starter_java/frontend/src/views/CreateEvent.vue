<template>
  <form-format>
    <form class="event">
      <h1>Create a New Event</h1>
      <form-line>
        <h4>Title:</h4>
        <input type="text" v-model="eventData.title">
      </form-line>
      <!-- <form-line>
        <h4>URL for the event image:</h4>
        <input type="text" v-model="eventData.eventImageURL">
      </form-line>-->
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

/* .select-box {
  max-height: max-content;
} */
</style>




