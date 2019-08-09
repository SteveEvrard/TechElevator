<template>
  <div class="whiskey-brands">
    <div>
      <multiselect
        class="whiskey-selection"
        v-model="selected"
        :options="listOfBrands"
        :multiple="true"
        :close-on-select="false"
        placeholder=" Pick whiskeys"
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

<style scoped>
.whiskey-selection {
  background-color: #f1f1f1;
  padding: 0px;
  text-align: center;
  
  border-radius: 5px;
}
</style>

<style scoped>
li:nth-child(odd) {
  background-color: #598899;
  
  padding: 0px;
  margin: 0px;
}
</style>