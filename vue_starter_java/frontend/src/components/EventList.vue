<template>
  <div class="event-list">
    <ul id="example-1">
      <li v-for="event in events" v-bind:key="event">
        <single-event v-bind: event="{{event}}"></single-event>
      </li>
    </ul>
  </div>
</template>

<script>
import SingleEvent from "../components/SingleEvent.vue";

export default {
  components: {
    SingleEvent
  },
  name: "EventList",
  props: {
    apiURL: String
  },
  data() {
    return {
      events: []
    };
  },
  methods: {
    createdEvents() {
      fetch(this.apiURL)
        .then(response => {
          return response.json();
        })
        .then(events => {
          this.events = events;
        })
        .catch(err => console.error(err));
    }
  }
};
</script>