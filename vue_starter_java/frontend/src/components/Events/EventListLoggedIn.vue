<template>
  <div class="event-list">
    <div v-for="singleEvent in publicEvents" :key="singleEvent">
      <single-event-logged-in :isHOME="isHome" :event="singleEvent"></single-event-logged-in>
    </div>
  </div>
</template>

<script>
import SingleEventLoggedIn from "@/components/Events/SingleEventLoggedIn.vue";
import CheckIn from "@/components/CheckIn.vue";
import auth from "@/auth";

export default {
  components: {
    SingleEventLoggedIn,
    CheckIn
  },
  name: "EventList",
  props: {
    apiURL: String,
    apiUrlByUser: String,
    isHOME: Boolean,
    childUserId: null
  },
  data() {
    return {
      events: [],
      userEvents: [],
      publicEvents: [],
      attendedEvents: [],
      loggedIn: Boolean(this.isLoggedIn),
      isHome: Boolean(this.isHOME)
    };
  },
  created() {
    this.createdEventsByUser();
    this.createdEvents();
  },
  methods: {
    createdEvents() {
      fetch(this.apiURL, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
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
    },
    createdEventsByUser() {
      fetch(this.apiUrlByUser + this.childUserId + "/events", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "application/json",
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonEvents => {
          this.userEvents = jsonEvents;
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
  flex-wrap: wrap;
}
li {
  list-style: none;
}
.event-list div:hover {
  opacity: 0.8;
}
</style>