<template>
  <div class="single-rating">
    <h1>Whiskey Rating Results</h1>
    <div class="rating-averages">
      <tile-format class="individual-rating">
        <h3>Taste</h3>
        <div class="star">
          <div class="rating" :style="percents.tasteRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Smell</h3>
        <div class="star">
          <div class="rating" :style="percents.smellRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Color</h3>
        <div class="star">
          <div class="rating" :style="percents.colorRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Body</h3>
        <div class="star">
          <div class="rating" :style="percents.bodyRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Finish</h3>
        <div class="star">
          <div class="rating" :style="percents.finishRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Price</h3>
        <div class="star">
          <div class="rating" :style="percents.priceRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <tile-format class="individual-rating">
        <h3>Overall Rating</h3>
        <div class="star">
          <div class="rating" :style="percents.overallRatingWidth">
            <span>&#x2605;&#x2605;&#x2605;&#x2605;&#x2605;</span>
          </div>
        </div>
      </tile-format>
      <!-- <button v-if="event.isBlindTasting">What whiskey was it?</button> -->
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
    percents: {
      tasteRatingWidth: Number,
      smellRatingWidth: Number,
      colorRatingWidth: Number,
      bodyRatingWidth: Number,
      finishRatingWidth: Number,
      priceRatingWidth: Number,
      overallRatingWidth: Number
    }
  },
  data() {
    return {
      event: {
        isBlindTasting: true
      },
      whiskey: {
        brand: "Jamison's Rye Whiskey"
      }
    };
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
</style>