<template>
  <div class="select-box" v-bind:id="tagId" v-on:click="selected(contentValue)">
    <slot></slot>
  </div>
</template>


<script>
export default {
  name: "SelectBox",
  data() {
    return {
      tagId: "not-selected",
      contentValue: ""
    };
  },
  methods: {
    selected(contentValue) {
      if (id === "selected") {
        tagId = "not-selected";
      } else {
        tagId = "selected";
        this.$emit("trackSelected");
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