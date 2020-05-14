<template>
  <div class="container-kit-hall" v-if="widthHall && heightHall">
    <div v-for="(item, index) in widthHall" :key="index">
      <div class="place" v-for="(item2, index2) in heightHall" :key="index2" @click="getPlace(index, index2)"></div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'AdminKitHall',
  components: {},
  props: {
    widthHall: {
      type: Number,
      default: 0
    },
    heightHall: {
      type: Number,
      default: 0
    }
  },
  methods: {
    getPlace (i, j) {
      console.log(i, j)
    }
  }
}
</script>

<style scoped lang="scss">
.container-kit-hall {
  background: #f3f3f3;
  height: 500px;
  width: 800px;
  display: flex;
}
.place {
  width: 20px;
  height: 20px;
  background: #16803c;
  margin: 5px;
}
</style>
