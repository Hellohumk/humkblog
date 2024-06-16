<template>
  用户名：<el-input v-model="inputuser_name" style="width: 240px" placeholder="Please input your username" /><br>
  <br>
  密码：<el-input v-model="inputpassword" style="width: 240px" placeholder="Please input your password" /><br>
  <br>
  <button @click="loginin">登录！</button>
</template>

<script>
import axios from 'axios'

export default {
    data(){
        return{
            inputuser_name:'',
            inputpassword:''
        }
    },methods:{
        loginin:function(){
            //axios
        axios.post('/login',{
          password: this.inputpassword,
          user_name: this.inputuser_name,
        }).then(res => {
          if(res.data.code == 1){//login success
            window.localStorage.setItem('token',res.data.data)
            window.location = '/#/blog/Home'
          }
          console.log(res.data)
        }).catch(error =>{
          console.log("fail to request :" + error)
        })
        }
    }
}
</script>

<style>

</style>
