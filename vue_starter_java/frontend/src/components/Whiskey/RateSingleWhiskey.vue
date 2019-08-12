<template>
  <form-format>
    <tile-format>
      <!-- <h2 v-if="!event.isBlindTasting">Rate This</h2> -->

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
      >></VueSlideBar>

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
      >></VueSlideBar>
      <rating-buttons-1-thru-5></rating-buttons-1-thru-5>

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
      >></VueSlideBar>
      <rating-buttons-1-thru-5></rating-buttons-1-thru-5>

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
      >></VueSlideBar>
      <rating-buttons-1-thru-5></rating-buttons-1-thru-5>

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
      >></VueSlideBar>

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
      >></VueSlideBar>

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
        v-on:israted="saveWhiskeyRating()"
      >></VueSlideBar>
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
  prop: {
    event: {
      title: "",
      // eventImageURL: "",
      isPrivate: true,
      date: new Date(),
      time: "",
      location: "",
      eventDescription: "",
      isBlindTasting: false
    }
  },
  data() {
    return {
      event: {
        isBlindTasting: false
      },
      whiskey: {
        brand: String,
        whiskeyId: Number
      },
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/users/",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
      whiskeyRating: {
        whiskeyId: Number,
        userId: Number,
        eventId: Number,
        tasteRating: this.slider.tasteValue,
        smellRating: this.slider.smellValue,
        colorRating: this.slider.colorValue,
        bodyRating: this.slider.bodyValue,
        finishRating: this.slider.finishValue,
        priceRating: this.slider.priceValue,
        overallRating: this.slider.overallValue
      },
      slider: {
        tasteValue: 3,
        smellValue: 3,
        colorValue: 3,
        bodyValue: 3,
        finishValue: 3,
        priceValue: 3,
        overallValue: 3,
        draggable: true,
        processStyle: {
          backgroundColor: "#2E4D58"
        }
      }
    };
  },
  created() {
    this.saveWhiskeyRating(event);
  },
  methods: {
    saveWhiskeyRating() {
      fetch(this.apiURLEvent, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(this.eventData)
      })
        .then(response => {
          if (response.ok) {
            this.$emit("showReviews");
            this.$router.push("/");
          }
        })
        .catch(err => console.error(err));
    }
  }
  // isFinished() {
  //   this.$emit("finished");
  // }
};
</script>

<style>
.create {
  text-align: center;
  align-items: center;
  align-self: center;
}

h4 {
  display: inline-block;
  margin: 20px 0px 10px 0px;
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




