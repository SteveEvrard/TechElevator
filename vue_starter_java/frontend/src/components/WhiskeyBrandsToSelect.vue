<template>
  <div class="whiskey-brands">
    <div>
      <multiselect v-model="selected" :options="whiskeys"></multiselect>
    </div>
  </div>
</template>

<script>
import Multiselect from "vue-multiselect";

export default {
  name: "WhiskeyBrandsToSelect",
  components: {
    Multiselect
  },
  props: {
    apiURL: String
  },
  data() {
    return {
      whiskeys: [],
      privateEvents: [],
      selected: null
    };
  },
  created() {
    this.existingWhiskeys();
  },
  methods: {
    existingWhiskeys() {
      fetch(this.apiURL)
        .then(response => {
          return response.json();
        })
        .then(jsonWhiskeys => {
          this.whiskeys = jsonWhiskeys;
          console.log(this.whiskeys);
        })
        .catch(err => console.error(err));
    }
  }
};
</script>