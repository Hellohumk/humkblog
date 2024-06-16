<template>
    <h1>博客主页</h1>

    <BlogSet :blogarray="blogarray"></BlogSet>

    <!-- 这里再提供一个多标签查询 -->
</template>

<script>
import BlogSet from '@/components/BlogSet.vue'
import axios from 'axios'

export default {
    components:{
        BlogSet
    },
    data(){
        return{
            blogarray: []
        }

    },methods:{

    },created(){
        axios.get("/blog/Home").then(res => {
            console.log(res.data);
            this.blogarray=res.data.data;
        }).catch(error => {
        console.log("fail to request :" + error);
      })
    }
};
</script>

<style>

</style>