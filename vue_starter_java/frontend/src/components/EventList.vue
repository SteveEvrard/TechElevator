<template>
  <div class="event-list">
    <div v-for="event in publicEvents" v-bind:key="event">
      <single-event v-bind:event="event"></single-event>
    </div>
  </div>
</template>

<script>
import SingleEvent from "../components/SingleEvent.vue";
import CheckIn from "../components/CheckIn.vue";

export default {
  components: {
    SingleEvent,
    CheckIn
  },
  name: "EventList",
  props: {
    apiURL: String
  },
  data() {
    return {
      events: [],
      publicEvents: [],
      attendedEvents: []
    };
  },
  created() {
    this.createdEvents();
  },
  methods: {
    createdEvents() {
      fetch(this.apiURL)
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonEvents => {
          this.events = jsonEvents;
          this.events.forEach(event => {
            if (!event.isPrivate) {
              this.publicEvents.push(event);
            }
          });
        })

        .catch(err => console.error(err));
    }
  }
};
</script>

<style>
.event-list {
  display: flex;
  justify-content: space-around;
  align-content: flex-start;
  margin: 15px 0px 15px 0px;
}
li {
  list-style: none;
}
</style>