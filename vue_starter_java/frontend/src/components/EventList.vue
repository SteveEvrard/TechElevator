<template>
  <div class="event-list">
    <ul>
      <li v-for="event in publicEvents" v-bind:key="event">
        <single-event v-bind:event="event"></single-event>
        
      </li>
    </ul>
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
      publicEvents: []
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
          console.log(jsonEvents);
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
li {
  list-style: none;
}
</style>