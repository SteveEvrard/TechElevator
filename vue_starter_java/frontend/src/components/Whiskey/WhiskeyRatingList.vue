<template>
  <div class="whiskey-list">
    <div v-for="whiskeyR in whiskeyRatingList" v-bind:key="whiskeyR">
      <single-whiskey-rating v-bind:whiskey="whiskeyR"></single-whiskey-rating>
    </div>
  </div>
</template>

<script>
import SingleEvent from "@/components/Events/SingleEvent.vue";
import CheckIn from "@/components/CheckIn.vue";
import TileFormat from "@/components/Formatting/TileFormat.vue";
import SingleWhiskeyRating from "@/components/Whiskey/SingleWhiskeyRating.vue";

export default {
  components: {
    SingleEvent,
    CheckIn,
    TileFormat,
    SingleWhiskeyRating
  },
  name: "WhiskeyRatingList",
  props: {
    eventId: null,
    whiskeyRating: {
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
  event: {
    title: String,
    imgUrl: String,
    date: new Date(),
    time: String,
    location: String,
    eventDescription: "",
    isBlindTasting: true
    // eventId: Number
  },
  created() {
    this.listWhiskeyRatings();
    console.log(this.whiskeyRatingApiUrl);
  },
  data() {
    return {
      numOfWhiskeys: "",
      numOfReviewers: "",
      whiskeyList: [],
      whiskeyRatingList: [],
      whiskeyBrandList: [],
      whiskeyReviewerList: [],
      whiskeyApiURL:
        "http://localhost:8080/AuthenticationApplication/api/event/",
      whiskeyRatingApiUrl:
        "http://localhost:8080/AuthenticationApplication/api/event/",
      tasteRatingPercentage: "",
      smellRatingPercentage: "",
      colorRatingPercentage: "",
      bodyRatingPercentage: "",
      finishRatingPercentage: "",
      priceRatingPercentage: "",
      overallRatingPercentage: "",
      sumTasteRating: "",
      sumSmellRating: "",
      sumColorRating: "",
      sumBodyRating: "",
      sumFinishRating: "",
      sumPriceRating: "",
      sumOverallRating: "",
      aveWhiskeyRating: {
        aveTasteRating: "",
        aveSmellRating: "",
        aveColorRating: "",
        aveBodyRating: "",
        aveFinishRating: "",
        avePriceRating: "",
        aveOverallRating: ""
      },
      whiskeyId: 9
    };
  },
  methods: {
    listWhiskeyRatings() {
      fetch(
        this.whiskeyRatingApiUrl +
          this.eventId +
          "/whiskeyRating/" +
          this.whiskeyId
      )
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(jsonList => {
          this.whiskeyRatingList = jsonList;
          console.log(this.whiskeyRatingList);
          this.doAllCalculations();
        })
        .catch(err => console.error(err));
    },
    countReviews(whiskeyRating) {
      if (!whiskeyReviewerList.contains(whiskeyRating.userId)) {
        this.numOfReviewers += 1;
        whiskeyReviewerList.push(whiskeyRating.userId);
      }
    },
    getAveragesOfAllRatings() {
      this.aveTasteRating = this.sumTasteRating / this.numOfWhiskeys;
      this.aveSmellRating = this.sumSmellRating / this.numOfWhiskeys;
      this.aveColorRating = this.sumColorRating / this.numOfWhiskeys;
      this.aveBodyRating = this.sumBodyRating / this.numOfWhiskeys;
      this.aveFinishRating = this.sumFinishRating / this.numOfWhiskeys;
      this.avePriceRating = this.sumPriceRating / this.numOfWhiskeys;
      this.aveOverallRating = this.sumOverallRating / this.numOfWhiskeys;
    },
    getPercentagesOfAllRatings() {
      this.tasteRatingPercentage = this.aveTasteRating / 5;
      this.smellRatingPercentage = this.aveSmellRating / 5;
      this.colorRatingPercentage = this.aveColorRating / 5;
      this.bodyRatingPercentage = this.aveBodyRating / 5;
      this.finishRatingPercentage = this.aveFinishRating / 5;
      this.priceRatingPercentage = this.avePriceRating / 5;
      this.overallRatingPercentage = this.aveOverallRating / 5;
    },
    getCountsForAverages(whiskeyRating) {
      addTasteRating(whiskeyRating.tasteRating);
      addSmellRating(whiskeyRating.smellRating);
      addColorRating(whiskeyRating.colorRating);
      addBodyRating(whiskeyRating.bodyRating);
      addFinishRating(whiskeyRating.finishRating);
      addPriceRating(whiskeyRating.priceRating);
      addOverallRating(whiskeyRating.overallRating);
    },
    doAllCalculations() {
      this.whiskeyRatingList.forEach(whiskeyRating => {
        this.countWhiskey(whiskeyRating);
        this.getCountsForAverages(whiskeyRating);
        this.getAveragesOfAllRatings();
        this.getPercentagesOfAllRatings();
      });
    },

    addTasteRating(tasteRating) {
      this.sumTasteRating += tasteRating;
    },
    addSmellRating(smellRating) {
      this.sumSmellRating += smellRating;
    },
    addColorRating(colorRating) {
      this.sumColorRating += colorRating;
    },
    addBodyRating(bodyRating) {
      this.sumBodyRating += bodyRating;
    },
    addFinishRating(finishRating) {
      this.sumFinishRating += finishRating;
    },
    addPriceRating(priceRating) {
      this.sumPriceRating += priceRating;
    },
    addOverallRating(overallRating) {
      this.sumOverallRating += overallRating;
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
</style>