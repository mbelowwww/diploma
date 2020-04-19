<template>
  <div class="main">
<!--    <AppInput v-model="testInput" title="Поле для ввода:" placeholder="Введите значение"/>-->
  </div>
</template>

<script>
import AppInput from '@/components/common/input/AppInput.vue'
export default {
  name: 'PageMain',
  components: {
    AppInput
  },
  data () {
    return {
      testInput: ''
    }
  }
}
</script>

<style scoped lang="scss">
.main {
  height: calc(100vh - 140px);
}
</style>
