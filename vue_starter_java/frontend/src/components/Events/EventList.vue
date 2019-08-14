<template>
  <div class="event-list-no-login">
    <div v-for="singleEvent in publicEvents" :key="singleEvent">
      <single-event :isHOME="isHome" :event="singleEvent"></single-event>
    </div>
  </div>
</template>

<script>
import SingleEvent from "@/components/Events/SingleEvent.vue";
import CheckIn from "@/components/CheckIn.vue";

export default {
  components: {
    SingleEvent,
    CheckIn
  },
  name: "EventList",
  props: {
    apiURL: String,
    apiUrlByUser: String,
    isLoggedInBool: Boolean,
    isHOME: Boolean,
    childUserId: Number,
    isAdminUser: Boolean
  },
  data() {
    return {
      events: [],
      publicEvents: [],
      isHome: Boolean(this.isHOME)
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
.event-list-no-login {
  display: flex;
  justify-content: space-around;
  align-content: flex-start;
  margin: 15px 0px 15px 0px;
  flex-wrap: wrap;
}
li {
  list-style: none;
}
</style>