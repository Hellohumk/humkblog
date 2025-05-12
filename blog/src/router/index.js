import { createRouter, createWebHashHistory } from 'vue-router'

import PersonalHome from '../views/personal/personalHome.vue'
import PersonalLike from '../views/personal/personalLike.vue'
import PersonalBlog from '../views/personal/personalBlog.vue'
import PersonalCommit from '../views/personal/personalCommit.vue'

import BlogHome from '../views/blog/blogHome.vue'
import PageText from '../views/blog/pageText.vue'

import Login from '../views/login/loginIn.vue'

// import UpdateMsg from '../views/updateMsg.vue'

const routes = [
  {
    path: '/api/personal/Home',
    name: 'personalhome',
    component: PersonalHome
  },
  {
    path: '/api/personal/Like',
    name: 'personallike',
    component: PersonalLike
  },
  {
    path: '/api/personal/Blog',
    name: 'personalblog',
    component: PersonalBlog
  },
  {
    path: '/api/personal/Commit',
    name: 'personalcommit',
    component: PersonalCommit
  },


  {
    path: '/api/blog/Home',
    name: 'bloghome',
    component: BlogHome
  },
  {
    path: '/api/blog/Page/:blog_id',
    name: 'pagetext',
    component: PageText
  },

  {
    path: '/api/login',
    name: 'loginin',
    component: Login
  },

  // {
  //   path:'/updatemsg',
  //   name:'updatemsg',
  //   component: UpdateMsg
  // }


]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
