<template>
  <form class="form">
    <slot></slot>
  </form>
</template>

<script>
export default {
  name: "FormFormat"
};
</script>

<style>
.form button {
  text-align: center;
  margin: 5%;
}

.form h1 {
  text-align: center;
  background: black;
  color: white;
}
.form h4 {
  font-size: 20px;
  align-content: center;
  text-align: center;
  align-items: center;
  align-self: center;
}
.form div {
  text-align: center;
  align-items: center;
  align-self: center;
}
</style>