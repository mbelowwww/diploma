<template>
  <div class="wrapper-buttons" :class="{'wrapper-buttons__vertical' : direction === 'vertical'}">
    <button v-for="button in buttons"
            :class="[{'button-active' : selected === button || (selected && selected.key === button.key)},button.class]"
            class="button_default"
            @click="$emit('click', button)">
      {{button.val}}
    </button>
  </div>
</template>

<script>
export default {
  components: {},
  props: {
    buttons: {
      type: Array,
      require: true
    },
    classProp: {
      type: String,
      require: false
    },
    selected: {
      require: true
    },
    direction: {
      type: String,
      default: '',
      require: false
    }
  }
}
</script>

<style scoped lang="scss">
  .wrapper-buttons {
    display: flex;
    &__vertical {
      flex-direction: column;
    }
  }
  .button_default {
    width: 180px;
    height: 40px;
    border: 2px solid white;
    border-radius: 5px;
    margin-right: 10px;
    color: white;
    background-color: rgba(255, 255, 255, 0);
    cursor: pointer;
  }
  .button-active {
    background-color: rgba(255, 255, 255, 1);
    color: black;
  }
</style>
