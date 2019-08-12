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
import TileFormat from "@/components/Formatting/TileFormat.vue";

export default {
  components: {
    TileFormat
  },
  name: "SingleWhiskeyRating",
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
      tasteRatingWidth: "",
      smellRatingWidth: "",
      colorRatingWidth: "",
      bodyRatingWidth: "",
      finishRatingWidth: "",
      priceRatingWidth: "",
      overallRatingWidth: ""
    };
  },
  created() {
    this.getRatingWidths();
    console.log(this.tasteRatingWidth);
  },
  methods: {
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