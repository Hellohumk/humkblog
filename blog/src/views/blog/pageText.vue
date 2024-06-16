<template>
    <div class="common-layout">
        <el-container>
            <el-main>
                <PersonShow :id="blog.writer_id"> </PersonShow>
                <!-- 解析文章并展示 折叠框 -->
                <div v-html="this.blog.blog_text"></div>
                <!-- 这里blog显示其他数据 -->
                <hr>
                <el-row :gutter="20">
                    <el-col :span="6"><div class="grid-content ep-bg-purple" >创建时间：{{ blog.create_time }}</div></el-col>
                    <el-col :span="6"><div class="grid-content ep-bg-purple" >最近一次修改：{{ blog.update_time }}</div></el-col>
                    <el-col :span="6"><div class="grid-content ep-bg-purple" >获赞总数：{{ blog.likes_sum }}</div></el-col>
                    <el-col :span="6"><div class="grid-content ep-bg-purple" >评论总数:{{ blog.commit_sum }}</div></el-col>
                </el-row>
                <button @click="onclickcommit">评论一下？</button>
                <el-input
                v-model="inputcommit"
                style="width: 240px"
                placeholder="Please input"
                clearable
                />
            </el-main>
            <el-footer>
                <!-- 无限滚动，后面做 -->
                <CommitSet :blog_id="this.blog.id"></CommitSet>
            </el-footer>
        </el-container>
  </div>
    
    
</template>

<script>
import PersonShow from '@/components/personShow.vue';
import CommitSet from '@/components/CommitSet.vue'
import axios from 'axios'

// import { ref } from 'vue'
// const count = ref(0)


export default {
    components:{
        PersonShow,
        CommitSet
    },
    data(){
        return{
            blog_id:'',
            blog:{
              
            },
            inputcommit:''
        }
    },methods:{
      onclickcommit:function(){
        const blog_id = this.$route.params.blog_id;
        this.blog_id = blog_id;
        axios.post('blog/Page',{
          blog_id : blog_id,
          commit_text: this.inputcommit
        }).then(res => {
          console.log(res.data)
          if(this.inputcommit=='')//评论不可为空
          //清空inputcommit
          this.inputcommit = ''
          if(res.data.code == 1){
            window.alert("评论成功")
          }
        }).catch(error =>{
          console.log("fail to request :" + error)
        })
      },

    },created(){
        const blog_id = this.$route.params.blog_id;
        this.blog_id = blog_id;
        axios.get('blog/Page',{
          params: {
            blog_id : blog_id
          }
        }).then(res => {
          console.log(res.data);
          this.blog = res.data.data;
      }).catch(error => {
        console.log("fail to request :" + error);
      })
    }
}
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

/* 折叠狂
.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
}
.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 10px;
  color: var(--el-color-primary);
}
.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
} */
</style>