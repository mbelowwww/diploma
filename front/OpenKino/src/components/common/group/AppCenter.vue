<template>
  <div class="center" :class="{'center__vertical' : isVertical}" :style="getMaxWidth">
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
    maxWidth: {
      type: String,
      default: '1440'
    }
  },
  computed: {
    getMaxWidth () {
      return `max-width: ${this.maxWidth}px`
    }
  }
}
</script>

<style scoped lang="scss">
.center {
  display: flex;
  width: 100%;
  margin: 0 auto;
  &__vertical {
    flex-direction: column;
  }
}
</style>
