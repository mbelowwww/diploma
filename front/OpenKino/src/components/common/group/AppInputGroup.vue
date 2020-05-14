<template>
  <div class="input-group">
    <slot name="group"></slot>
  </div>
</template>

<script>
export default {
  name: 'AppInputGroup'
}
</script>

<style scoped lang="scss">
  .input-group {
     display: flex;
   }
</style>
