<template>
  <label class="container">
    <span v-if="title"
          class="container__title"
    >
      {{title}}
    </span>
    <input :type="type"
           :disabled="isDisabled"
           :value="value"
           :placeholder="placeholder"
           :class="[classProp ? classProp : '']"
           :style="setSizeInput"
           class="container__input"
           @input="$emit('input', $event.target.value)"
    />
  </label>
</template>

<script>
export default {
  components: {},
  props: {
    value: {
      /* Здесь должен быть хотя бы type */
    },
    title: {
      type: String,
      default: ''
    },
    type: {
      type: String,
      default: 'text'
    },
    isDisabled: {
      type: Boolean,
      default: false
    },
    placeholder: {
      type: String,
      default: ''
    },
    classProp: {
      type: String,
      default: ''
    },
    inputSize: {
      type: String,
      default: ''
    }
  },
  computed: {
    setSizeInput () {
      return `min-width: ${this.inputSize}px`
    }
  }
}
</script>

<style scoped lang="scss">
.container {
  margin: 10px 0;
  &__title {
    color: #222222;
    background: white;
  }
  &__input {
    width: 100%;
    height: 40px;
    border-radius: 3px;
    border: 1px solid black;
    padding: 4px;
    font-size: 18px;
    margin: 5px 0;
  }
  &__input-line {
    border-bottom: 2px solid #222222;
    border-top: none;
    border-left: none;
    border-right: none;
  }
}

</style>
