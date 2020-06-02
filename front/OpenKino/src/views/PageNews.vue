<template>
  <AppCenter>
    <div class="item-news">
      <div class="item-news__block-image">
        <img src="@/assets/img/imageFilm.jpg" width="362px" height="190px" alt="news">
      </div>
      <div class="item-news__block-info">
        <p>Оставшиеся в живых члены команды Мстителей и их союзники должны разработать новый план, который поможет противостоять разрушительным действиям могущественного титана Таноса. После наиболее масштабной и трагической битвы в истории они не могут допустить ошибку.</p>
        <div class="wrapper-btn"><button class="btn-cabinet">Подробнее</button></div>
      </div>
    </div>
  </AppCenter>
</template>

<script>
import AppCenter from '../components/common/group/AppCenter'
export default {
  components: { AppCenter }
}
</script>

<style scoped lang="scss">
.item-news {
  display: flex;
  height: 200px;
  width: 100%;
  background-color: rgba(22, 22, 22, 0.9);
  &__block-image {
    flex-grow: 1;
    padding: 5px;
  }
  &__block-info {
    flex-grow: 2;
    padding: 10px 10px 10px 20px;
    font-size: 20px;
    letter-spacing: 1px;
    font-weight: 100;
  }
}
</style>
