<template>
  <div>
    <form-format class="event-background">
      <h1>Create a New Event</h1>

      <h4>Title:</h4>
      <br>
      <input type="text" v-model="eventData.title">
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
      <input type="text" v-model="eventData.time">
      <br>

      <h4>Location:</h4>
      <br>
      <input type="text" v-model="eventData.location">
      <br>

      <h4>Additional Information:</h4>
      <br>
      <textarea v-model="eventData.eventDescription"></textarea>
      <br>

      <h4>Is this a blind tasting?</h4>
      <input type="checkbox" v-model="eventData.isBlindTasting">
      {{eventData.isBlindTasting ? "yes" : "no"}}
      <br>
      <h4>What whiskeys will be tasted?</h4>
      <h4>
        <whiskey-brands-to-select :apiURL="API_URL"></whiskey-brands-to-select>
      </h4>
      <br>
      <h4>
        <button type="submit" v-on:click.prevent="saveEvent">Create Event</button>
      </h4>
    </form-format>
  </div>
</template>

<script>
import FormFormat from "../components/FormFormat.vue";
import WhiskeyBrandsToSelect from "../components/WhiskeyBrandsToSelect.vue";

export default {
  components: {
    FormFormat,
    WhiskeyBrandsToSelect
  },

  data() {
    return {
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
            let eventId = this.event.eventId;
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
  background: #f1f1f1;
  margin: 0px;
}
</style>




