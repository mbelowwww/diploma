module.exports = {
  lintOnSave: false,
  css: {
    loaderOptions: {
      scss: {
        prependData: `
          @import "@/assets/style/variables.scss";
          @import "@/assets/style/clearfix.scss";
          @import "@/assets/style/mixins.scss";
          `
      }
    }
  }
}
