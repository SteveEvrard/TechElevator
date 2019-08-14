<template>
  <div class="whiskey-brands">
    <div>
      <multiselect
        class="whiskey-selection"
        v-model="selected"
        @mouseover.native="hover = true"
        @mouseleave.native="hover = false"
        :options="listOfBrands"
        :multiple="true"
        :close-on-select="false"
        placeholder= "Click Here To Pick Whiskeys"
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
      selected: [],
      hover: false
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

<style scoped>
.whiskey-selection {
  background-color:#75787b;
  padding: 10px;
  text-align: center;
  border-radius: 5px;
}

.whiskey-selection:hover {
  background-color: #75787b;
  padding: 10px;
  text-align: center;

  border-radius: 5px;
}

</style>