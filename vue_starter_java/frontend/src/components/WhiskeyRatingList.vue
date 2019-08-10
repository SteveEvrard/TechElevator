<template>
  <div class="whiskey-list"></div>
</template>

<script>
import SingleEvent from "../components/SingleEvent.vue";
import CheckIn from "../components/CheckIn.vue";
import TileFormat from "../components/TileFormat.vue";

export default {
  components: {
    SingleEvent,
    CheckIn,
    TileFormat
  },
  name: "WhiskeyRatingList",
  props: {
    ratingApiURL: String,
    whiskeyApiURL: String,
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
    },
    event: {
      title: String,
      imgUrl: String,
      date: new Date(),
      time: String,
      location: String,
      eventDescription: "",
      isBlindTasting: true
    },
    data() {
      return {
        sumTasteRating: "",
        sumSmellRating: "",
        sumColorRating: "",
        sumBodyRating: "",
        sumFinishRating: "",
        sumPriceRating: "",
        sumOverallRating: "",
        numOfWhiskeys: "",
        numOfReviewers: "",
        whiskeyList: [],
        whiskeyRatingList: [],
        whiskeyBrandList: [],
        whiskeyReviewerList: []
      };
    },
    created() {
      this.listWhiskeyRatings();
    },
    methods: {
      listWhiskeyRatings() {
        fetch(this.ratingApiURL)
          .then(response => {
            console.log(response);
            return response.json();
          })
          .then(jsonList => {
            this.whiskeyRatingList = jsonList;
            getAveragesOfAllRatings();
            getPercentagesOfAllRatings();
          })
          .catch(err => console.error(err));
      },
      getExistingWhiskeys() {
        fetch(this.apiURL)
          .then(response => {
            return response.json();
          })
          .then(jsonWhiskeys => {
            this.whiskeyList = jsonWhiskeys;
          })
          .catch(err => console.error(err));
      },
      countWhiskey(whiskeyRating) {
        if (!whiskeyBrandList.contains(whiskeyRating.whiskeyId)) {
          this.numOfWhiskeys += 1;
          whiskeyBrandList.push(whiskeyRating.whiskeyId);
        }
      },
      countReviews(whiskeyRating) {
        if (!whiskeyReviewerList.contains(whiskeyRating.userId)) {
          this.numOfReviewers += 1;
          whiskeyReviewerList.push(whiskeyRating.userId);
        }
      }
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

.star {
  width: 150px;
  position: relative;
  color: #75787b;
}

.rating {
  overflow: hidden;
  white-space: nowrap;
}

.rating span {
  font-size: 30px;
  white-space: nowrap;
  overflow: hidden;
  color: #787121;
}

.rating span:before {
  content: "\2606\2606\2606\2606\2606";
  position: absolute;
  color: #75787b;
  z-index: -1;
}
</style>