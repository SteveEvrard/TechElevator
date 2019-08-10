<template>
  <rate-all-whiskeys></rate-all-whiskeys>
</template>

<script>
import RateAllWhiskeys from "../components/RateAllWhiskeys.vue";

export default {
  components: {
    RateAllWhiskeys
  },
  name: "eventResponse",
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/whiskeys"
    };
  }
};
</script>