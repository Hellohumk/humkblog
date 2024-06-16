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
    path: '/personal/Home',
    name: 'personalhome',
    component: PersonalHome
  },
  {
    path: '/personal/Like',
    name: 'personallike',
    component: PersonalLike
  },
  {
    path: '/personal/Blog',
    name: 'personalblog',
    component: PersonalBlog
  },
  {
    path: '/personal/Commit',
    name: 'personalcommit',
    component: PersonalCommit
  },


  {
    path: '/blog/Home',
    name: 'bloghome',
    component: BlogHome
  },
  {
    path: '/blog/Page/:blog_id',
    name: 'pagetext',
    component: PageText
  },

  {
    path: '/login',
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
