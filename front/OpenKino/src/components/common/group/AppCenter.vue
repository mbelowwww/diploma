<template>
  <div class="center" :class="{'center__vertical' : isVertical, 'center__admin' : isAdmin}">
    <slot></slot>
  </div>
</template>

<script>
export default {
  name: 'AppCenter',
  props: {
    isVertical: {
      type: Boolean,
      default: false
    },
    isAdmin: {
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
  &__admin {
    max-width: 1000px;
  }
}
</style>
