<template>
    <h1>这里是我的评论</h1>
    <Commit v-for="commit in commits" :key="commit" :id="commit"></Commit>
</template>

<script>
import axios from 'axios'
import Commit from '@/components/PersonCommit.vue'


export default {
    components:{
        Commit
    },
    data(){
      return{
        //这是ids，别把自己骗了
        commits:[]
      }
  },methods:{
    
  },created() {
    //jwt
    axios.get('personal/Commit').then(res => {
            console.log(res.data)
            this.commits = res.data.data
        }).catch(error => {
            console.log("FAIL TO REQUEST: " + error)
        })
  }
}
</script>

<style>

</style>

