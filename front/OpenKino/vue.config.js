module.exports = {
  lintOnSave: false,
  devServer: {
    proxy: 'http://localhost:8083'
  },
  css: {
    loaderOptions: {
      scss: {
        prependData: `
          @import "@/assets/style/variables.scss";
          @import "@/assets/style/clearfix.scss";
          @import "@/assets/style/mixins.scss";
          @import "@/assets/style/stylesForComponents/button.scss";
          `
      }
    }
  }
}
