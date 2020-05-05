<template>
  <div class="container-show">
    <span v-if="showItemMenu.val">Вы выбрали: {{showItemMenu.val ? showItemMenu.val : ''}}</span>
    <span v-else>Вы ничего не выбрали</span>
  </div>
</template>

<script>
export default {
  name: 'AdminShow',
  components: {},
  data () {
    return {
    }
  },
  props: {
    showItemMenu: {
      type: Object,
      require: true
    }
  }
}
</script>

<style scoped lang="scss">
.container-show {
  width: 100%;
  height: 50px;
  background: #f1f1f1;
  font-size: 26px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #222222;
  font-weight: 700;
}
</style>
