import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import filter from './filter'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
// import errorHandler from './handler/errorHandler'

// axios.defaults.withCredentials = true;
//Mock
// import './mockjs'

createApp(App).use(ElementPlus).use(router).use(filter).mount('#app')
// 得先创建对象，才可以用
axios.defaults.baseURL = "http://101.42.41.45:8848"


//解决报错问题
const debounce = (fn, delay) => {
    let timer
     return (...args) => {
       if (timer) {
         clearTimeout(timer)
       }
       timer = setTimeout(() => {
         fn(...args)
       }, delay)
     }
  }

const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver{
   constructor(callback) {
     callback = debounce(callback, 200);
     super(callback);
   }
}

import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}





