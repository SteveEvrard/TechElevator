<template>
  <div class="whiskey-list">
    <single-whiskey-rating v-bind:percents="widths"></single-whiskey-rating>
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
      widths: {
        tasteRatingWidth: "",
        smellRatingWidth: "",
        colorRatingWidth: "",
        bodyRatingWidth: "",
        finishRatingWidth: "",
        priceRatingWidth: "",
        overallRatingWidth: ""
      },
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
          this.doAllCalculations();
        })
        .catch(err => console.error(err));
    },
    countReviews() {
      this.numOfReviewers = this.whiskeyRatingList.length;
    },
    getAveragesOfAllRatings() {
      this.aveWhiskeyRating.aveTasteRating =
        this.sumTasteRating / this.numOfReviewers;
      this.aveWhiskeyRating.aveSmellRating =
        this.sumSmellRating / this.numOfReviewers;
      this.aveWhiskeyRating.aveColorRating =
        this.sumColorRating / this.numOfReviewers;
      this.aveWhiskeyRating.aveBodyRating =
        this.sumBodyRating / this.numOfReviewers;
      this.aveWhiskeyRating.aveFinishRating =
        this.sumFinishRating / this.numOfReviewers;
      this.aveWhiskeyRating.avePriceRating =
        this.sumPriceRating / this.numOfReviewers;
      this.aveWhiskeyRating.aveOverallRating =
        this.sumOverallRating / this.numOfReviewers;
    },
    getPercentagesOfAllRatings() {
      this.tasteRatingPercentage =
        (this.aveWhiskeyRating.aveTasteRating / 5) * 10;
      this.smellRatingPercentage =
        (this.aveWhiskeyRating.aveSmellRating / 5) * 10;
      this.colorRatingPercentage =
        (this.aveWhiskeyRating.aveColorRating / 5) * 10;
      this.bodyRatingPercentage =
        (this.aveWhiskeyRating.aveBodyRating / 5) * 10;
      this.finishRatingPercentage =
        (this.aveWhiskeyRating.aveFinishRating / 5) * 10;
      this.priceRatingPercentage =
        (this.aveWhiskeyRating.avePriceRating / 5) * 10;
      this.overallRatingPercentage =
        (this.aveWhiskeyRating.aveOverallRating / 5) * 10;
    },
    getRatingWidths() {
      this.widths.tasteRatingWidth =
        "width: " + this.tasteRatingPercentage + "%";
      this.widths.smellRatingWidth =
        "width: " + this.smellRatingPercentage + "%";
      this.widths.colorRatingWidth =
        "width: " + this.colorRatingPercentage + "%";
      this.widths.bodyRatingWidth = "width: " + this.bodyRatingPercentage + "%";
      this.widths.finishRatingWidth =
        "width: " + this.finishRatingPercentage + "%";
      this.widths.priceRatingWidth =
        "width: " + this.priceRatingPercentage + "%";
      this.widths.overallRatingWidth =
        "width: " + this.overallRatingPercentage + "%";
    },
    doAllCalculations() {
      this.whiskeyRatingList.forEach(whiskeyRating => {
        this.sumTasteRating += whiskeyRating.tasteRating;
        this.sumSmellRating += whiskeyRating.smellRating;
        this.sumColorRating += whiskeyRating.colorRating;
        this.sumBodyRating += whiskeyRating.bodyRating;
        this.sumFinishRating += whiskeyRating.finishRating;
        this.sumPriceRating += whiskeyRating.priceRating;
        this.sumOverallRating += whiskeyRating.overallRating;
        this.countReviews(whiskeyRating);
        this.getAveragesOfAllRatings();
        this.getPercentagesOfAllRatings();
        this.getRatingWidths();
      });
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