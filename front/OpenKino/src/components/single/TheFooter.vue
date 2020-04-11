<template>
  <div class="footer">
    {{textFooter}}
  </div>
</template>

<script>
export default {
  data () {
    return {
      textFooter: '© 2006-2020 Сеть кинотеатров «OpenKino» - все права защищены.'
    }
  }
}
</script>

<style scoped lang="scss">
.footer {
  width: 100%;
  color: white;
  background: #545454;
  height: 30px;
  padding: 5px;
  text-align: center;
}
</style>
