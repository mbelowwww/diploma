<template>
  <label class="container">
    <span v-if="title"
          class="container__title"
    >
      {{title}}
    </span>
    <input :type="type"
           :value="value"
           class="container__input"
           @input="$emit('input', $event.target.value)"
    />
  </label>
</template>

<script>
export default {
  components: {},
  props: {
    title: {
      type: String,
      default: ''
    },
    value: {
      type: String,
      default: ''
    },
    config: {
      type: Object
    },
    type: {
      type: String,
      default: 'text'
    }
  }
}
</script>

<style scoped lang="scss">
.container {
  &__title {
    color: #1EA4FF;
  }
  &__input {
    width: 100%;
    height: 40px;
  }
}
</style>
