<template>
  <div class="form-line">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: "FormLine"
};
</script>

<style scoped>
.form-line {
  max-height: 5%;
  display: flex;
  justify-content: flex-end;
  align-content: center;
  align-items: center;
  margin-right: 25%;
  flex-wrap: nowrap;
}

h4 {
  margin-right: 5%;
  flex-grow: 1;
}

.checkbox {
  margin-right: 45%;
}

input {
  flex-grow: 2;
  padding: 1%;
  border-radius: 3px;
  background-color: #f1f1f1;
}
</style>