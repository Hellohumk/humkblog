<template>

    <el-card style="max-width: 480px">
    <template #header>
        <!-- 卡片头部 -->
      <div class="card-header">
        <router-link to='/blog/Page' @click="goto()">{{ title }}</router-link>
      </div>
    </template>
    <!-- 内容 -->
    <!-- 这里应该解析文件 document并且部分展示-->
    <!-- 卡片尾部 -->
    <template #footer>
        <div>最后更新时间:{{ update_time }}</div>
        <div>博客id:{{ ID }}</div>
        <button @click="updatelike">加入我的喜欢</button>
    </template>
  </el-card>


</template>

<script>
import axios from 'axios'

export default {
    name:"aBlog",
    props:["ID","title","update_time","document"],
    data(){
      return{

      }
    },methods:{
      //page传参
      goto:function(){
        this.$router.push({
          name:'pagetext',
          params: {
            blog_id: this.ID
          }
        })
      },
      updatelike:function(){
        //axios 拿到jwt id 加入对应喜欢
        axios.post('blog',{
          blog_id: this.ID
        }).then(res => {
          alert("已添加到我的喜欢")
          console.log(res.data)
        }).catch(error =>{
          console.log("fail to request :" + error)
        })
      }
    }
}
</script>

<style>
.el-card{
  display:flex;
  align-items: center;
  justify-content: center;
}

</style>