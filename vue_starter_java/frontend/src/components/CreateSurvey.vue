<template>
    <div class="survey">
        <h1 class="whiskeyName">
            {{title}}
        </h1>
<div class="questions">
    <form-format v-for="question in questions" v-bind:key="question">
    <tile-format>
        {{question}}
    <input type="text" v-model="answers">
    
    </tile-format>
    </form-format>
    <button type="submit">Submit your answers!</button>
</div>
    </div>
</template>

<script>
import TileFormat from "../components/TileFormat";
import FormFormat from "../components/FormFormat";
import SingleEvent from "../components/SingleEvent";
import SingleWhiskeyRating from "../components/SingleWhiskeyRating";

export default {
    components: {
        FormFormat
    },
    data() {
    return {
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/events",
    prop: {
    eventData: {
      title: "",
      // eventImageURL: "",
      isPrivate: true,
      date: new Date(),
      time: "",
      location: "",
      eventDescription: "",
      isBlindTasting: true,
      questions:[],
      answers:[]
    },
      methods: {
    saveSurvey() {
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
}
}
    }
}
</script>

<style>

</style>