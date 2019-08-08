<template>
  <div class="select-box" v-bind:id="tagId" v-on:click="selected(whiskey.brand)">{{whiskey.brand}}</div>
</template>


<script>
export default {
  name: "SingleWhiskeySelectBox",
  data() {
    return {
      tagId: "not-selected",
      whiskeys: [],
      contentValue: ""
    };
  },
  methods: {
    selected(brand) {
      if (id === "selected") {
        tagId = "not-selected";
      } else {
        tagId = "selected";
        whiskeys.add(brand);
        this.$emit("trackSelected", this.whiskeys);
      }
    }
  }
};
</script>

<style>
.select-box {
  display: inline-block;
  color: white;
  padding: 10px;
  max-width: content;
  height: content;
  border-radius: 3px;
}

#not-selected {
  background-color: #2e4d58;
  border: 1px solid #000000;
}

#selected {
  background-color: #598899;
  border: 1px solid #2e4d58;
}
</style>

<style scoped>
p {
  margin: 0px;
}
</style>