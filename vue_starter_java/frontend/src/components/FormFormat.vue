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
button {
  text-align: center;
  margin: 5%;
}

h1 {
  text-align: center;
  background: black;
  color: white;
}
h4 {
  font-size: 20px;
  align-content: center;
  text-align: center;
  align-items: center;
  align-self: center;
}
div {
  text-align: center;
  align-items: center;
  align-self: center;
}
</style>