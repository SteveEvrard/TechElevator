<template>
  <div class="form">
    <tile-format>
      <slot></slot>
    </tile-format>
  </div>
</template>

<script>
import TileFormat from "../components/TileFormat.vue";

export default {
  components: {
    TileFormat
  },
  name: "FormFormat"
};
</script>

<style>
.form {
  display: block;
}
</style>