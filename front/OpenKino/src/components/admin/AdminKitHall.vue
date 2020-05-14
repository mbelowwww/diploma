<template>
  <div class="container-kit-hall">
    <div v-for="(item, index) in places" :key="index">
      <div class="place" v-for="(item2, index2) in item" :key="index2" @click="getPlace(index, index2)"></div>
    </div>

  </div>
</template>

<script>
export default {
  name: 'AdminKitHall',
  components: {},
  props: {
    places: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
    }
  },
  methods: {
    getPlace (i, j) {
      // console.log('x: ', i, 'y: ', j)
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
