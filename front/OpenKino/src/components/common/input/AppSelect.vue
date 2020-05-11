<template>
  <div class="sp-field">
    <span class="sp-field__label">{{ title  ? title : '&zwnj;'}}</span>
    <label
      class="sp-field__input"
      :class="[propClass, {'sp-field__input_error': isShowError}]"
    >
      <input
        class="sp-input"
        :value="valueByKey"
        :disabled="disabled"
        :placeholder="placeholder"
        readonly
        @blur="isFocus = true"
        @click.prevent.stop="clickByInput"
      />
    </label>
    <AppList
      v-if="isFocus"
      :list="values"
      class-container="search__list"
      @click="selectedInputValue($event)"
    >
      <template #item="{item}">
        <slot name="option" :value="item">
          {{item.val}}
        </slot>
      </template>
    </AppList>
  </div>
</template>

<script>
import AppList from './AppList.vue'
export default {
  name: 'AppSelect',
  components: { AppList },
  props: {
    value: {
      require: true
    },
    values: {
      type: Array,
      require: true
    },
    propClass: {
      type: String,
      require: false
    },
    isShowError: {
      type: Boolean,
      require: false
    },
    disabled: {
      type: Boolean,
      require: false,
      default: false
    },
    inputKey: {
      type: String,
      default: 'val'
    },
    title: {
      type: String
    },
    placeholder: {
      type: String
    }
  },
  mounted () {
    const vm = this
    function closeDropDown () { vm.isFocus = false }
    document.addEventListener('click', closeDropDown)
    this.$once('hook:beforeDestroy', function () {
      removeEventListener('click', closeDropDown)
    })
  },
  data () {
    return {
      isFocus: false
    }
  },
  computed: {
    valueByKey () {
      if (!this.value) return ''
      if (this.inputKey.indexOf('.') === -1) {
        return this.value[this.inputKey]
      }

      let value = this.value
      const keyArray = this.inputKey.split('.')
      keyArray.forEach((item) => {
        value = value[item]
      })
      return value
    }
  },
  methods: {
    clickByInput () {
      this.isFocus = true
    },
    selectedInputValue (value) {
      this.setValue(value)
      this.isFocus = false
    },
    setValue (value) {
      this.$emit('input', value)
      this.$emit('update:value', value)
      this.$emit('change', value)
    }
  }
}
</script>

<style scoped lang="scss">
  .sp-field {
    position: relative;
    display: flex;
    flex-direction: column;
    width: 100%;
    color: black;
    &__label {
      /*@include setFont(Raleway-Bold, 0.8rem);*/
      margin-bottom: 6px;
    }

    &__input {
      display: flex;
      padding: 5px;
      border: 1px solid $gray;
      box-shadow: 3px 0px 1px $gray;
      margin-right: 5px;
      background: white;
      height: 40px;
      &_error {
        box-shadow: 3px 0px 0px red;
      }
    }
  }

  .sp-input {
    border: 0;
    width: 100%;
    background: white;
    font-size: 18px;
  }

  .search__list {
    width: 100%;
    color: black;
    position: absolute;
    top: 100%;
    max-height: 300px;
    overflow: auto;
    background-color: rgba(255, 255, 255, 0.95);
  }
</style>
