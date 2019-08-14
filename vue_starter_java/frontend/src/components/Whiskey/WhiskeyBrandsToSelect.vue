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
        placeholder="Click Here To Pick Whiskeys"
      />
    </div>
  </div>
</template>
        
        

<script>
import Multiselect from "vue-multiselect";
import auth from "@/auth";

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
      fetch(this.apiURL, {
        method: "GET",
        headers: {
          Authorization: "Bearer " + auth.getToken()
        },
        body: JSON.stringify(this.eventData)
      })
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
<<<<<<< HEAD
  background-color:#75787b;
  padding: 10px;
  text-align: center;
  border-radius: 5px;
=======
  background-color: white;
  padding: 10px;
  text-align: center;
>>>>>>> 6a464df4ac9a62079dc05ab8e6e22b0dac792e4c
}

.whiskey-selection:hover {
  background-color: #75787b;
  padding: 10px;
  text-align: center;

  border-radius: 5px;
}
</style>