<template>
  <tile-format class="single-event">
    <div v-on:click="showdetail(event)">
      <h2>{{event.title}}</h2>
      <!-- <img src="{{event.imgUrl}}"> -->
      <div id="line-div">
        <h4>When:</h4>
        <p>{{event.date[1]}} / {{event.date[2]}} / {{event.date[0]}} at {{event.time}}</p>
      </div>
      <div id="line-div">
        <h4>Where:</h4>
        <p>{{event.location}}</p>
      </div>
      <div v-if="!isHomePage">
        <div id="description">
          <h4>About the Event</h4>
          <p>{{event.eventDescription}}</p>
        </div>
        <h4 id="table-label">Your Ratings:</h4>
        <!-- <table class="table" v-if="!isHomePage">
          <tr>
            <th>Whiskey</th>
            <th>Taste</th>
            <th>Smell</th>
            <th>Color</th>
            <th>Finish</th>
            <th>Price</th>
            <th>Overall</th>
          </tr>
          <tr v-for="rating in whiskeyRatingList" :key="rating">
            <td>{{rating.whiskey.brand}}</td>
            <td>{{rating.tasteRating}}</td>
            <td>{{rating.smellRating}}</td>
            <td>{{rating.colorRating}}</td>
            <td>{{rating.finishRating}}</td>
            <td>{{rating.priceRating}}</td>
            <td>{{rating.overallRating}}</td>
          </tr>
        </table>-->
      </div>
    </div>
  </tile-format>
</template>

<script>
import TileFormat from "@/components/Formatting/TileFormat.vue";

export default {
  components: {
    TileFormat
  },
  props: {
    isHOME: Boolean,
    event: {
      eventId: Number,
      title: String,
      // imgUrl: String,
      date: new Date(),
      time: String,
      location: String,
      eventDescription: String,
      isPrivate: Boolean,
      isBlindTasting: Boolean
    },
    whiskeyRating: {
      whiskey: {
        brand: String,
        price: Number
      },
      whiskeyId: Number,
      userId: Number,
      eventId: Number,
      tasteRating: Number,
      smellRating: Number,
      colorRating: Number,
      bodyRating: Number,
      finishRating: Number,
      priceRating: Number,
      overallRating: Number
    }
  },
  data() {
    return {
      isHomePage: Boolean(this.isHOME),
      isLoggedIn: true,
      hasRated: false
    };
  },
  name: "SingleEvent",
  methods: {
    showdetail(event) {
      let eventId = this.event.eventId;
      this.$router.push({ name: "eventDetailPage", params: { eventId } });
    }
  }
};
</script>

<style>
table {
  border: 1px solid #f1f1f1;
  border-collapse: collapse;
  margin: 2% 3% 1% 3%;
}
td,
th {
  padding: 6px;
  border: 1px solid #f1f1f1;
}
th {
  background-color: rgba(120, 113, 33, 0.5);
  font-size: 1.3em;
}

.single-event h4 {
  margin: 10px 0px 5px 0px;
  font-size: 1.5em;
  font-weight: 500px;
  padding-right: 10px;
  display: inline-block;
  color: black;
}
.single-event p {
  margin: 0px;
  display: inline-block;
  font-size: 1.2em;
}
.single-event h2 {
  text-align: center;
  margin: 10px;
  font-family: "Great Vibes", sans-serif;
  font-size: 2.5em;
  font-weight: 300px;
}
.single-event {
  padding: 5px;
  background-color: white;
  width: 350px;
  height: 250px;
}

#line-div {
  width: 100%;
  display: block;
  padding: 5px 20px 5px 20px;
}
#description {
  margin-top: 25px;
  margin-bottom: 10px;
  text-align: center;
  width: 80%;
  padding-right: 10%;
  padding-left: 10%;
}
#description h4 {
  margin-bottom: 10px;
}
#table-label {
  width: fit-content;
  padding-right: 35%;
  padding-left: 35%;
  margin-top: 20px;
}
</style>