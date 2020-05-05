<template>
  <div class="container-header">
    <div class="container-header__block"></div>
    <div class="container-header__block">
      <span class="container-header__block__title">Здравствуйте, {{nameAdmin ? nameAdmin : 'администратор'}}</span>
      <img src="@/assets/img/exit.png" alt="exit" width="24px" height="21px" class="container-header__block__image" @click="$router.push({name: 'PageMaine'})">
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminHeader',
  components: {},
  data () {
    return {
      nameAdmin: ''
    }
  }
}
</script>

<style scoped lang="scss">
.container-header {
  height: 130px;
  width: 100%;
  background: white;
  display: flex;
  justify-content: space-between;
  &__block {
    color: #222222;
    display: flex;
    align-items: center;
    &__image {
      margin: 0 30px;
    }
    &__title {
      font-size: 24px;
    }
   }
}
</style>
