<template>
  <label class="sp-field" :class="inputConf.class">
        <span v-if="inputConf.label" style="margin-bottom: 5px">
            <span v-if="!inputConf.hideLabel" class="sp-field__label">{{ inputConf.label ? inputConf.label : '&zwnj;'}}</span>
            <span v-if="inputConf.valid" class="sp-field__valid">{{inputConf.valid ? inputConf.valid : ''}}</span>
        </span>

    <DatePicker class="sp-field__input sp-field__date"
                 input-class="sp-field__input__date"
                 :placeholder="inputConf.placeholder"
                 :type="inputConf.type ? inputConf.type : 'date'"
                 :class="inputConf.inputClass"
                 :value="value"
                 :value-type="inputConf.valueType"
                 :minuteStep="inputConf.minuteStep"
                 :disabled="inputConf.disabled || disabledInput"
                 :disabled-date="disDate"
                 :format="format"
                 :input-attr="inputAttr"
                 @input="$emit('input', $event)"
    />

  </label>
</template>

<script>
import DatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css'
import 'vue2-datepicker/locale/ru'
export default {
  name: 'AppDatePicker',
  components: { DatePicker },
  props: {
    value: {
    },
    inputConf: {
      type: Object
    },
    disDate: {
      type: () => Boolean
    },
    inputAttr: {
      type: Object
    },
    disabledInput: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
    }
  },
  computed: {
    format () {
      return this.inputConf.type === 'time' ? 'HH:mm' : 'DD.MM.YYYY HH:mm:ss'
    }
  },
  methods: {
    input ($event) {
      $event.preventDefault()
      if (!$event.target) return
      if (this.value.length === 10) {
        this.$emit('input', this.value)
      } else if (this.value.length === 1 || this.value.length === 4) {
        this.$emit('input', $event.target.value + '.')
      } else {
        this.$emit('input', $event.target.value)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .sp-field {
    display: flex;
    flex-direction: column;
    max-width: 200px;

    &__label {
      margin-bottom: 5px;
    }

    &__valid {
      font-size: 14px;
      color: #ff3d31;
      margin-left: 2px;
    }

    &__input {
      border: 1px solid gray;
      /*box-shadow: 3px 0 1px gray;*/
      background: white;
      &_error {
        box-shadow: 3px 0 0 red;
      }
    }

    &__date {
      width: 200px;
      height: 40px;
      background: white;
      line-height: 40px;
    }

    ::v-deep &__input__date {
      border: 0;
      padding: 13px 5px 10px;
      width: 100%;
    }
  }

</style>
