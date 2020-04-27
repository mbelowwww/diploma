<template>
  <div class="container-popup" @click.prevent.self="closeWindow">
    <slot></slot>
  </div>
</template>

<script>
export default {
  components: {},
  data () {
    return {
    }
  },
  methods: {
    closeWindow () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>
  .container-popup {
    width: 100vw;
    height: 100vh;
    background-color: rgba(24, 17, 4, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 999999;
    position: fixed;
    left: 0;
    top: 0;
  }
</style>
