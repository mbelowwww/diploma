<template>
  <div class="container-films">
    <AppTable :header="headers" :array="listFilms" headerValue="val" width="1440"/>
  </div>
</template>

<script>
import AppTable from '../components/common/table/AppTable'
export default {
  components: { AppTable },
  data () {
    return {
      listFilms: [],
      headers: [
        {
          id: 1,
          val: 'Название фильма',
          value: 'name'
        },
        {
          id: 2,
          val: 'Описание фильма',
          value: 'description'
        },
        {
          id: 3,
          val: 'Рейтинг',
          value: 'rating'
        },
        {
          id: 4,
          val: 'Возр. ограничение',
          value: 'limitAge.age'
        },
        {
          id: 5,
          val: 'Жанр',
          value: 'genre.name'
        }
      ]
    }
  },
  created () {
    this.$store.dispatch('films/getFilm', '').then(response => this.listFilms = response)
  }
}
</script>

<style scoped lang="scss">
  .container-films {
    background-color: rgba(26, 17, 28, 0.9);
    height: 800px;
    width: 100%;
    display: flex;
    padding: 20px;
    flex-direction: column;
  }
</style>
