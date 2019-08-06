<template>
  <div class="event-list"></div>
</template>

<script>
export default {
  name: "EventList",
  props: {
    apiURL: String
  },
  data() {
    return {
      events: []
    };
  }
  //   },
  //   methods: {
  //     createEvents() {
  //       fetch(this.apiURL, {
  //         method: "POST",
  //         headers: {
  //           "Content-Type": "application/json"
  //         },
  //         body: JSON.stringify(this.event)
  //       })
  //         .then(response => {
  //           if (response.ok) {
  //             this.$emit("showEvents");
  //           }
  //         })
  //         .catch(err => console.error(err));
  //     }
  //   }
  // };
};
</script>