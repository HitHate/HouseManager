const { defineConfig } = require('@vue/cli-service')
const AutoImport = require('unplugin-auto-import/webpack')
const Components = require('unplugin-vue-components/webpack')
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  //  ui 组件
  configureWebpack: {
    plugins: [
      AutoImport({
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
    ],
  },
  //跨域
  devServer: {

    // 启动后自动打开浏览器
    // open: true,
    // 端口 设置
    // port: '7070',
    proxy: {
      '/api': {
        target: 'http://localhost:8088',
        // target: 'http://192.168.245.152:8088',
        pathRewrite: { '^/api': '' }
      }
    },
    // 跳过host检查
    // historyApiFallback: true,
    // allowedHosts: "all"
  }
})
