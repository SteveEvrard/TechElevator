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


<style>
.tile {
  border-radius: 5px;
  border: 1px solid #f1f1f1;
  padding: 2%;
  margin: 2%;
  box-shadow: 1px 1px #75787b;
}
</style>