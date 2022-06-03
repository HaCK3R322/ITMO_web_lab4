import * as Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import App from './App.vue'
import router from './router'

Vue.createApp(App).use(router).use(VueAxios, axios).mount('#app')
