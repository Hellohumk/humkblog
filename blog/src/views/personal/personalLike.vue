<template>
    <h1>这里是我喜欢的博客:</h1>
    <el-input-number v-model="Deletenum" :min="1" :max="10" @change="handleChange" />
    <button @click="deletelike">删一个从我的喜欢</button>
    <hr>
    <div class="card">
        <BlogSet :blogarray="blogarray"></BlogSet>
    </div>
    
    
    
</template>

<script>
import axios from 'axios'
import BlogSet from '@/components/BlogSet.vue'
// import { Delete } from '@element-plus/icons-vue/dist/types';

export default {
  components: {
      BlogSet
    },
    data(){
        return{
            blogarray: [
            ],
            Deletenum:''
        }
    },methods:{
        deletelike:function(){
            //axios
            axios.delete("/personal/Like",{
                params:{
                    blog_id: this.Deletenum  
                }
                }).then(res => {
                    alert("添加成功")
                    console.log(res.data)
                }).catch(error => {
                    console.log("fail to request :" + error);
                })
        }
    },
    created() {
        //axios
        axios.get('/personal/Like').then(response => {
            console.log(response.data);
            this.blogarray = response.data.data;
        }).catch(error => {
            console.log("fail to request :" + error);
        })
    },
};
</script>

<style>
.card{
  align-items: center;
}

</style>