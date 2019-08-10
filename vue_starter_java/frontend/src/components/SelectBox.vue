<template>
  <div class="select-box">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "SelectBox"
};
</script>

<style>
.select-box {
  display: inline-block;
  background: #2e4d58;
  color: white;
  padding: 5px 10px 5px 10px;
  border-radius: 0px;
  font-size: 1.1em;
  margin: 0px 5px 0px 5px;
  border-radius: 3px;
}

.select-box:hover {
  background: #527785;
  border: 2px solid #2e4d58;
  color: white;
}

.select-box::selection {
  background: white;
  color: #2e4d58;
}
</style>




