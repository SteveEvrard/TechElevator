<template>
  <div class="single-rating">
    <h1>Whiskey Rating Results</h1>
    <div class="rating-averages">
      <h2 v-if="!event.isBlindTasting">{{whiskey.brand}}</h2>
      <tile-format>
        <h3>Taste</h3>
        <div class="star">
          <div class="rating" :style="tasteRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format>
        <h3>Smell</h3>
        <div class="star">
          <div class="rating" :style="smellRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format>
        <h3>Color</h3>
        <div class="star">
          <div class="rating" :style="colorRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format>
        <h3>Body</h3>
        <div class="star">
          <div class="rating" :style="bodyRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format>
        <h3>Finish</h3>
        <div class="star">
          <div class="rating" :style="finishRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format>
        <h3>Price</h3>
        <div class="star">
          <div class="rating" :style="priceRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format>
        <h3>Overall Rating</h3>
        <div class="star">
          <div class="rating" :style="overallRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <button v-if="event.isBlindTasting">What whiskey was it?</button>
    </div>
  </div>
</template>

<script>
import TileFormat from "../components/TileFormat.vue";

export default {
  components: {
    TileFormat
  },
  name: "SingleEvent",
  props: {
    whiskeyRatingList: Array,
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
  data() {
    return {
      event: {
        isBlindTasting: true
      },
      whiskey: {
        brand: "Jamison's Rye Whiskey"
      },
      tasteRatingPercentage: 52.3,
      smellRatingPercentage: 33.777777,
      colorRatingPercentage: 88,
      bodyRatingPercentage: 84.8,
      finishRatingPercentage: 60.2,
      priceRatingPercentage: 9.9,
      overallRatingPercentage: 99.9,
      tasteRatingWidth: "",
      smellRatingWidth: "",
      colorRatingWidth: "",
      bodyRatingWidth: "",
      finishRatingWidth: "",
      priceRatingWidth: "",
      overallRatingWidth: "",
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
      }
    };
  },
  created() {
    this.getRatingWidths();
    console.log(this.tasteRatingWidth);
  },
  methods: {
    getCountsForAverages() {
      this.whiskeyRatingList.forEach(whiskeyRating => {
        addTasteRating(whiskeyRating.tasteRating);
        addSmellRating(whiskeyRating.smellRating);
        addColorRating(whiskeyRating.colorRating);
        addBodyRating(whiskeyRating.bodyRating);
        addFinishRating(whiskeyRating.finishRating);
        addPriceRating(whiskeyRating.priceRating);
        addOverallRating(whiskeyRating.overallRating);
        getAveragesOfAllRatings();
        getPercentagesOfAllRatings();
      });
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
    },
    getRatingWidths() {
      this.tasteRatingWidth = "width: " + this.tasteRatingPercentage + "%";
      this.smellRatingWidth = "width: " + this.smellRatingPercentage + "%";
      this.colorRatingWidth = "width: " + this.colorRatingPercentage + "%";
      this.bodyRatingWidth = "width: " + this.bodyRatingPercentage + "%";
      this.finishRatingWidth = "width: " + this.finishRatingPercentage + "%";
      this.priceRatingWidth = "width: " + this.priceRatingPercentage + "%";
      this.overallRatingWidth = "width: " + this.overallRatingPercentage + "%";
    }
  }
};
</script>

<style scoped>
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
  font-size: 40px;
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
</style>