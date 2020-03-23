<template>
  <div class="center" :class="{'center__vertical' : vertical}">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'AppCenter',
  props: {
    vertical: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style scoped lang="scss">
.center {
  display: flex;
  width: 100%;
  max-width: 1440px;
  margin: 0 auto;
  &__vertical {
    flex-direction: column;
  }
}
</style>
