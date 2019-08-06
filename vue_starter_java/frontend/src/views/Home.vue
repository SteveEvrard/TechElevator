<template>
  <div>
    <div class="home">
      <h1>Home</h1>
      <p>You must be authenticated to see this</p>
    </div>
    <!-- <event-list
      v-if="!showAddProductForm"
      :apiURL="API_URL"
      v-on:addReview="addReview"
      v-on:editReview="editReview($event)"
    />-->
  </div>
</template>

<script>
// import EventList from "../components/EventList.vue";

export default {
  // components: {
  //   EventList
  // },
  name: "home",
  data() {
    return {
      API_URL: "http://localhost:8080/whiskey-events-api/api/events",
      showAddProductForm: false,
      reviewID: 0
    };
  },
  methods: {
    addReview() {
      this.showAddProductForm = true;
    },
    editReview(id) {
      this.reviewID = id;
      this.showAddProductForm = true;
    },
    showReviews() {
      this.showAddProductForm = false;
      this.reviewID = 0;
    }
  }
};
</script>
