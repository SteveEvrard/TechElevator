<template>
  <div class="tile">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "TileFormat"
};
</script>


<style scoped>
.tile {
  background: white;
  border-radius: 5px;
  border: 1px solid #f1f1f1;
  padding: 2%;
  margin: 2%;
  box-shadow: -2px 10px 18px -4px rgba(0, 0, 0, 0.75);
}
</style>