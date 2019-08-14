<template>
  <div class="single-rating">
    <h1>Whiskey Rating Results</h1>
    <div class="rating-averages">
      <tile-format class="individual-rating">
        <h3>Taste</h3>
        <div class="flex-score">
          <p>{{this.fourUpTaste}} people rated the taste 4 or higher.</p>

          <div class="star">
            <div class="rating" :style="widths.tasteRatingWidth">
              <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
            </div>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Smell</h3>
        <div class="flex-score">
          <p>{{this.fourUpSmell}} people rated the smell 4 or higher.</p>

          <div class="star">
            <div class="rating" :style="widths.smellRatingWidth">
              <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
            </div>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Color</h3>
        <div class="flex-score">
          <p>{{this.fourUpColor}} people rated the color 4 or higher.</p>

          <div class="star">
            <div class="rating" :style="widths.colorRatingWidth">
              <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
            </div>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Body</h3>
        <div class="flex-score">
          <p>{{this.fourUpBody}} people rated the body 4 or higher.</p>

          <div class="star">
            <div class="rating" :style="widths.bodyRatingWidth">
              <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
            </div>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Finish</h3>
        <div class="flex-score">
          <p>{{this.fourUpFinish}} people rated the finish 4 or higher.</p>

          <div class="star">
            <div class="rating" :style="widths.finishRatingWidth">
              <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
            </div>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Price</h3>
        <div class="flex-score">
          <p>{{this.fourUpPrice}} people rated the price 4 or higher.</p>

          <div class="star">
            <div class="rating" :style="widths.priceRatingWidth">
              <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
            </div>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Overall Rating</h3>
        <div class="flex-score">
          <p>{{this.fourUpPrice}} people rated the whiskey overall 4 or higher.</p>
          <div class="star">
            <div class="rating" :style="widths.overallRatingWidth">
              <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
            </div>
          </div>
        </div>
      </tile-format>
      <button v-if="blind">What whiskey was it?</button>
    </div>
  </div>
</template>

<script>
import TileFormat from "@/components/Formatting/TileFormat.vue";

export default {
  components: {
    TileFormat
  },
  name: "SingleWhiskeyRating",
  props: {
    whiskeyRatingList: Array,
    eventId: null,
    blind: Boolean,
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
  created() {
    this.listWhiskeyRatings();
  },
  data() {
    return {
      event: {
        isBlindTasting: true,
        numOfWhiskeys: "",
        numOfReviewers: "",

        whiskeyRatingList: [],
        whiskeyReviewerList: []
      },
      whiskeyRatingApiUrl:
        "http://localhost:8080/AuthenticationApplication/api/event/",
      tasteRatingPercentage: "",
      smellRatingPercentage: "",
      colorRatingPercentage: "",
      bodyRatingPercentage: "",
      finishRatingPercentage: "",
      priceRatingPercentage: "",
      overallRatingPercentage: "",
      fourUpTaste: "",
      fourUpSmell: "",
      fourUpColor: "",
      fourUpBody: "",
      fourUpFinish: "",
      fourUpPrice: "",
      fourUpOverall: "",
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
    checkIfOver4(rating) {
      if (rating.tasteRating >= 4) {
        this.fourUpTaste += 1;
      } else if (rating.smellRating >= 4) {
        this.fourUpSmell += 1;
      } else if (rating.colorRating >= 4) {
        this.fourUpColor += 1;
      } else if (rating.bodyRating >= 4) {
        this.fourUpBody += 1;
      } else if (rating.finishRating >= 4) {
        this.fourUpFinish += 1;
      } else if (rating.priceRating >= 4) {
        this.fourUpPrice += 1;
      } else if (rating.overallRating >= 4) {
        this.fourUpOverall += 1;
      }
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
        this.checkIfOver4(whiskeyRating);
        this.countReviews(whiskeyRating);
        this.getAveragesOfAllRatings();
        this.getPercentagesOfAllRatings();
        this.getRatingWidths();
      });
    }
  }
};
</script>

<style scoped>
h3 {
  font-size: 1.5em;
  font-weight: bold;
}
.star {
  width: 150px;
  position: relative;
  color: #f1f1f1;
}

.rating {
  overflow: hidden;
  white-space: nowrap;
}

.rating span {
  font-size: 60px;
  white-space: nowrap;
  overflow: hidden;
  color: #787121;
}

.rating span:before {
  content: "\2606\2606\2606\2606\2606";
  position: absolute;
  color: #f1f1f1;
  z-index: -1;
}
.individual-rating {
  padding: 20px 0px 30px 40px;
}
.rating-averages {
  min-width: 200px;
  max-width: 1200px;
  display: block;
  margin-left: 20%;
  margin-right: 20%;
}
.flex-score {
  display: flex;
}
</style>