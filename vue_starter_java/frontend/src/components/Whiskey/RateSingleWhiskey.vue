<template>
  <form-format>
    <tile-format>
      <h2 v-if="!event.isBlindTasting">{{whiskey.brand}}</h2>
      <h2 v-if="event.isBlindTasting">{{whiskey.whiskeyId}}</h2>

      <h4>
        Rate the
        <h4 class="emphasis-word">taste</h4>of the whiskey:
      </h4>
      <rating-buttons-1-thru-5
        v-bind:variableData="whiskeyRating.tasteRating"
        v-on:israted="whiskeyRating.tasteRating=rating"
      ></rating-buttons-1-thru-5>

      <h4>
        Rate the
        <h4 class="emphasis-word">smell</h4>of the whiskey:
      </h4>
      <rating-buttons-1-thru-5
        v-bind:variableData="whiskeyRating.smellRating"
        v-on:israted="whiskeyRating.smellRating=rating"
      ></rating-buttons-1-thru-5>

      <h4>
        Rate the
        <h4 class="emphasis-word">color</h4>of the whiskey:
      </h4>
      <rating-buttons-1-thru-5
        v-bind:variableData="whiskeyRating.colorRating"
        v-on:israted="whiskeyRating.colorRating=rating"
      ></rating-buttons-1-thru-5>

      <h4>
        Rate the
        <h4 class="emphasis-word">body</h4>of the whiskey:
      </h4>
      <rating-buttons-1-thru-5
        v-bind:variableData="whiskeyRating.bodyRating"
        v-on:israted="whiskeyRating.bodyRating=rating"
      ></rating-buttons-1-thru-5>

      <h4>
        Rate the
        <h4 class="emphasis-word">finish</h4>of the whiskey:
      </h4>
      <rating-buttons-1-thru-5
        v-bind:variableData="whiskeyRating.finishRating"
        v-on:israted="whiskeyRating.finishRating=rating"
      ></rating-buttons-1-thru-5>

      <h4>
        Rate the
        <h4 class="emphasis-word">price</h4>of the whiskey:
      </h4>
      <rating-buttons-1-thru-5
        v-bind:variableData="whiskeyRating.priceRating"
        v-on:israted="whiskeyRating.priceRating=rating, saveWhiskeyRating()"
      ></rating-buttons-1-thru-5>

      <h4 class="emphasis-word">What did you think of the whiskey overall?</h4>
      <rating-buttons-1-thru-5
        v-bind:variableData="whiskeyRating.overallRating"
        v-on:israted="whiskeyRating.overallRating=rating"
      ></rating-buttons-1-thru-5>
    </tile-format>
  </form-format>
</template>

<script>
import TileFormat from "@/components/Formatting/TileFormat.vue";
import FormFormat from "@/components/Formatting/FormFormat.vue";
import RatingButtons1Thru5 from "@/components/Formatting/RatingButtons1Thru5.vue";
import SelectBox from "@/components/Formatting/SelectBox.vue";

export default {
  components: {
    TileFormat,
    FormFormat,
    RatingButtons1Thru5,
    SelectBox
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
      apiURLEvent: "http://localhost:8080/AuthenticationApplication/api/events",
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys",
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
    };
  },
  created() {
    this.updateWhiskeyRating(event);
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
  },
  rateTaste(value) {},
  updateWhiskeyRating(event) {
    this.whiskeyRating.eventId = this.event.eventId;
  }
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




