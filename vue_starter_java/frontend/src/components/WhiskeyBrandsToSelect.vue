<template>
  <div class="whiskey-brands">
    <div v-for="whiskey in whiskeys" v-bind:key="whiskey" v-on:click="selected(contentValue)">
      <select-box v-bind:whiskey="whiskey"></select-box>
    </div>
  </div>
</template>

<script>
import SelectBox from "../components/SelectBox.vue";

export default {
  components: {
    SelectBox
  },
  name: "WhiskeyBrands",
  props: {
    apiURL: String
  },
  data() {
    return {
      whiskeys: [],
      privateEvents: []
    };
  },
  methods: {
    createdEvents() {
      fetch(this.apiURL)
        .then(response => {
          return response.json();
        })
        .then(whiskeys => {
          this.whiskeys = whiskeys;
        })
        .catch(err => console.error(err));
    },
    listPrivateEvents(events) {
      array.forEach(event => {
        if (event.isPrivate) {
          privateEvents.add(event);
        }
      });
    }
  }
};
</script>