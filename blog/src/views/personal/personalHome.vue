<template>
  <div id="building">
  <!-- 这里差个头像的地址没上传 -->
  <div class="personal_home_image">
      <el-avatar :src="user.image"  :size="300" shape="square"> 头像 </el-avatar>
      <span> 
        <el-button type="text" @click="dialogVisible = true">修改我的信息</el-button>
            <!-- 这个属性使她是否可见 -->
            <el-dialog title="表单弹框" v-model="dialogVisible" width="30%">
                <aForm> </aForm>
                
            </el-dialog>

      </span>
  </div>   
  <hr>
  <span>用户名：{{ user.user_name }}</span><br>
  <span>经验：{{ user.exp }}</span><br>
  <span>发布数目：{{ user.user_blog_sum }}</span><br>
  <hr>
  <router-link to="/personal/Like">我的喜欢</router-link>
  <p></p>
  <router-link to="/personal/Blog">我的博客</router-link>
  <p></p>
  <router-link to="/personal/Commit">我的评论</router-link>

</div>
</template>

<script>
import axios from 'axios'
import aForm from "@/components/aForm.vue"


export default{
  components:{
    aForm

  },
  data(){
      return{
        user :
          { },
        // percentage : this.user.
        // progressformat : this.user.exp%100,
        // 弹窗
        dialogVisible:false,
        
      }
  },methods:{
    closedialog(){
      this.dialogVisible = false
    }
  },
    mounted() {
      axios.get('personal/Home').then(response => {
        console.log(response.data);
        this.user = response.data.data;
      }).catch(error => {
        console.log("fail to request :" + error);
      })
    },

}

</script>

<style scoped>

.personal_home_image {
  width: 300px;
  height: 300px;
  display: flex;
  text-align: center;
  width:100%;	

}

/* background */
#building{
  /* background:url('~@/assets/background.jpg'); */
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}

</style>