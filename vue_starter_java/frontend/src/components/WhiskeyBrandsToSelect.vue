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

<style>
.whiskey-selection {
  background-color: #f1f1f1;
  padding: 10px;
  text-align: center;
  width: fit-content;
  border-radius: 5px;
}
</style>

<style scoped>
li:nth-child(odd) {
  background-color: #598899;
  width: 100%;
  padding: 10px;
  margin: 5px;
}
</style>