<template>
  <div>
    <div class="rating-choice" v-on:click="variableData=1, rating=1">1</div>
    <div class="rating-choice" v-on:click="variableData=2, rating=2">2</div>
    <div class="rating-choice" v-on:click="variableData=3, rating=3">3</div>
    <div class="rating-choice" v-on:click="variableData=4, rating=4">4</div>
    <div class="rating-choice" v-on:click="variableData=5, rating=5">5</div>
  </div>
</template>

<script>
export default {
  name: "SelectBox",
  components: {},
  prop: {
    variableData: String
  },
  data() {
    return {
      rating: ""
    };
  },
  created() {
    this.passValues();
  },
  methods: {
    isRated() {
      if (rating > 0) {
        return true;
      } else {
        return false;
      }
    },
    passValues() {
      this.$emit(isRated(), rating);
    }
  }
};
</script>

<style>
.rating-choice {
  display: inline-block;
  background: #2e4d58;
  color: white;
  padding: 5px 10px 5px 10px;
  border-radius: 0px;
  font-size: 1.1em;
  margin: 0px 5px 0px 5px;
  border-radius: 3px;
}

.rating-choice:hover {
  background: #527785;
  border: 2px solid #2e4d58;
  color: white;
}

.rating-choice::selection {
  background: white;
  color: #2e4d58;
}
</style>




