<template>
  <div>
    <div class="nav">
      <router-link class="nav-link" v-bind:to="{ name: 'homeLoggedIn' }">Home</router-link>
      <router-link class="nav-link" :to="{ name: 'home' }">Logout</router-link>
    </div>
    {{survey.question}}
  </div>
</template>

<script>
import auth from "../auth";

export default {
  name: "eventSurveyPage",

  data() {
    return {
      eventID: 0,
      surveyAPI: "http://localhost:8080/AuthenticationApplication/api/survey/",
      survey: {
        eventId: 0,
        question: ""
      }
    };
  },
  created() {
    this.eventID = this.$route.params.eventId;
  },
  methods: {
    getSurvey() {
      fetch(this.urveyAPI + this.eventID, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        }
      })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonSurvey => {
          this.survey = jsonSurvey;
        })
        .catch(err => console.error(err));
    }
  }
};
</script>