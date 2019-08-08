<template>
  <div class="whiskey-brands">
    <div>
      <multiselect
        v-model="selected"
        :options="listOfBrands"
        :multiple="true"
        :close-on-select="false"
        placeholder="Pick whiskeys"
      />
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
      listOfBrands: [],
      selected: []
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
          this.whiskeys.forEach(element => {
            this.listOfBrands.push(element.brand);
          });
        })
        .catch(err => console.error(err));
    }
  }
};
</script>