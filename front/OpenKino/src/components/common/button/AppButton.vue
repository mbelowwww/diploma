<template>
  <div class="wrapper-buttons" :class="{
    'wrapper-buttons__vertical' : direction === 'vertical',
    'wrapper-buttons__between' : isBetween
    }">
    <button v-for="(button, index) in buttons"
            :key="index"
            :class="[{'button-active' : selected === button || (selected && selected.key === button.key)},
            {'button-auth-active' : selected === button || (selected && selected.key === button.key)}, button.class]"
            class="button_default"
            @click="choosedButton(button)">
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
    },
    isBetween: {
      type: Boolean,
      default: false
    }
  },
  date () {
    return {
      newSelected: {}
    }
  },
  methods: {
    choosedButton (button) {
      this.newSelected = this.buttons.find(item => item.key === button.key)
      this.$emit('click', this.newSelected)
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
    &__between {
      justify-content: space-between;
    }
  }
  .button_default {
    min-width: 180px;
    height: 40px;
    border: 2px solid white;
    border-radius: 5px;
    margin-right: 10px;
    color: white;
    background-color: rgba(255, 255, 255, 0);
    cursor: pointer;
    padding: 5px;
  }
  .button-active {
    background-color: rgba(255, 255, 255, 1);
    color: black;
  }
  .button-auth {
    font-size: 24px;
    color: #222222;
  }
  .button-entry {
    font-size: 20px;
    background: #0071BE;
  }
  .button-registration {
    font-size: 20px;
    background: #0071BE;
  }
  .button-reset-password {
    color: #0E649E;
    font-size: 18px;
    text-decoration: underline;
  }
  .btn-primary {
    width: 100%;
    max-width: 200px;
    height: 40px;
    padding: 10px;
    background: #5eaaff;
    color: #222222;
    border: none;
    border-radius: 3px;
    font-size: 18px;
    margin: 15px 0 0 0;
    &:hover {
      cursor: pointer;
      background: #0E649E;
      color: white;
    }
  }
</style>
