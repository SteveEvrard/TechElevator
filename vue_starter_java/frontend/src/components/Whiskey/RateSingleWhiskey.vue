<template>
  <form-format>
    <tile-format>
      <div class="rating-questions-and-sliders">
        <h4>
          Rate the
          <h4 class="emphasis-word">taste</h4>of the whiskey:
        </h4>
        <VueSlideBar
          class="slider"
          v-model="slider.tasteValue"
          :processStyle="slider.processStyle"
          :labelStyles="{ color: 'white', backgroundColor: 'transparent' }"
          :min="1"
          :max="5"
          :lineHeight="20"
          :tooltipStyles="{ backgroundColor: '#2E4D58', borderColor: '#2E4D58' }"
          :draggable="true"
        ></VueSlideBar>

        <h4>
          Rate the
          <h4 class="emphasis-word">smell</h4>of the whiskey:
        </h4>
        <VueSlideBar
          class="slider"
          v-model="slider.smellValue"
          :processStyle="slider.processStyle"
          :labelStyles="{ color: 'white', backgroundColor: 'transparent' }"
          :min="1"
          :max="5"
          :lineHeight="20"
          :tooltipStyles="{ backgroundColor: '#2E4D58', borderColor: '#2E4D58' }"
          :draggable="true"
        ></VueSlideBar>

        <h4>
          Rate the
          <h4 class="emphasis-word">color</h4>of the whiskey:
        </h4>
        <VueSlideBar
          class="slider"
          v-model="slider.colorValue"
          :processStyle="slider.processStyle"
          :labelStyles="{ color: 'white', backgroundColor: 'transparent' }"
          :min="1"
          :max="5"
          :lineHeight="20"
          :tooltipStyles="{ backgroundColor: '#2E4D58', borderColor: '#2E4D58' }"
          :draggable="true"
        ></VueSlideBar>

        <h4>
          Rate the
          <h4 class="emphasis-word">body</h4>of the whiskey:
        </h4>
        <VueSlideBar
          class="slider"
          v-model="slider.bodyValue"
          :processStyle="slider.processStyle"
          :labelStyles="{ color: 'white', backgroundColor: 'transparent' }"
          :min="1"
          :max="5"
          :lineHeight="20"
          :tooltipStyles="{ backgroundColor: '#2E4D58', borderColor: '#2E4D58' }"
          :draggable="true"
        ></VueSlideBar>

        <h4>
          Rate the
          <h4 class="emphasis-word">finish</h4>of the whiskey:
        </h4>
        <VueSlideBar
          class="slider"
          v-model="slider.finishValue"
          :processStyle="slider.processStyle"
          :labelStyles="{ color: 'white', backgroundColor: 'transparent' }"
          :min="1"
          :max="5"
          :lineHeight="20"
          :tooltipStyles="{ backgroundColor: '#2E4D58', borderColor: '#2E4D58' }"
          :draggable="true"
        ></VueSlideBar>

        <h4>
          Rate the
          <h4 class="emphasis-word">price</h4>of the whiskey:
        </h4>
        <VueSlideBar
          class="slider"
          v-model="slider.priceValue"
          :processStyle="slider.processStyle"
          :labelStyles="{ color: 'white', backgroundColor: 'transparent' }"
          :min="1"
          :max="5"
          :lineHeight="20"
          :tooltipStyles="{ backgroundColor: '#2E4D58', borderColor: '#2E4D58' }"
          :draggable="true"
        ></VueSlideBar>

        <h4 class="emphasis-word">What did you think of the whiskey overall?</h4>
        <VueSlideBar
          class="slider"
          v-model="slider.overallValue"
          :processStyle="slider.processStyle"
          :labelStyles="{ color: 'white', backgroundColor: 'transparent' }"
          :min="1"
          :max="5"
          :lineHeight="20"
          :tooltipStyles="{ backgroundColor: '#2E4D58', borderColor: '#2E4D58' }"
          :draggable="true"
        ></VueSlideBar>
        <button type="submit" v-on:click.prevent="saveWhiskeyRating">{{submitMessage()}}</button>
      </div>
    </tile-format>
  </form-format>
</template>

<script>
import TileFormat from "@/components/Formatting/TileFormat.vue";
import FormFormat from "@/components/Formatting/FormFormat.vue";
import VueSlideBar from "vue-slide-bar";

export default {
  components: {
    TileFormat,
    FormFormat,
    VueSlideBar
  },
  name: "RateSingleWhiskey",
  props: {
    rswEvent: {
      eventId: Number,
      title: String,
      imgUrl: String,
      date: new Date(),
      time: String,
      location: String,
      info: String,
      // tastingWhiskeys: Array,
      isPrivate: Boolean,
      isBlindTasting: Boolean
    }
  },
  data() {
    return {
      event: {
        isBlindTasting: false
      },
      isSubmitted: false,
      whiskey: {
        brand: String,
        whiskeyId: Number
      },
      whiskeyRating: {
        whiskeyId: 9,
        userId: 1,
        eventId: Number,
        tasteRating: Number,
        smellRating: Number,
        colorRating: Number,
        bodyRating: Number,
        finishRating: Number,
        priceRating: Number,
        overallRating: Number
      },
      whiskeyRatingAPI:
        "http://localhost:8080/AuthenticationApplication/api/users/",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
      slider: {
        tasteValue: 3,
        smellValue: 3,
        colorValue: 3,
        bodyValue: 3,
        finishValue: 3,
        priceValue: 3,
        overallValue: 3,
        draggable: false,
        processStyle: {
          backgroundColor: "#2E4D58"
        }
      }
    };
  },
  methods: {
    saveWhiskeyRating() {
      this.isSubmitted = true;
      this.assignData();
      fetch(
        this.whiskeyRatingAPI +
          1 +
          "/" +
          this.eventId +
          "/" +
          9 +
          "/whiskeyRating",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(this.whiskeyRating)
        }
      ).catch(err => console.error(err));
      this.$emit(submit);
    },
    assignData() {
      this.whiskeyRating.tasteRating = this.slider.tasteValue;
      this.whiskeyRating.smellRating = this.slider.smellValue;
      this.whiskeyRating.colorRating = this.slider.colorValue;
      this.whiskeyRating.bodyRating = this.slider.bodyValue;
      this.whiskeyRating.finishRating = this.slider.finishValue;
      this.whiskeyRating.priceRating = this.slider.priceValue;
      this.whiskeyRating.overallRating = this.slider.overallValue;
      this.whiskeyRating.eventId = this.rswEvent.eventId;
    },
    submitMessage() {
      if (this.isSubmitted) {
        return "SUBMITTED";
      } else {
        return "SUBMIT  REVIEW";
      }
    }
  }
};
</script>

<style>
h4 {
  display: inline-block;
  margin: 20px 0px 10px 0px;
}
.slider {
  margin: 0% 30% 0% 30%;
}

#rating-choice {
  background: #2e4d58;
  color: white;
  padding: 5px 10px 5px 10px;
  border-radius: 0px;
  font-size: 1.1em;
  margin: 0px 5px 0px 5px;
  border-radius: 3px;
}

#rating-choice:hover {
  background: #527785;
  border: 2px solid #2e4d58;
  color: white;
}

#rating-choice::selection {
  background: white;
  color: #2e4d58;
}

.emphasis-word {
  color: #9d432c;
  padding-right: 5px;
  padding-left: 2px;
  font-size: 1.1em;
}
</style>




