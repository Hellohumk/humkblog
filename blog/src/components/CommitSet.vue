<template>
    <CommitCommon v-for="id in ids" :key="id" :father_id="id"></CommitCommon>
</template>

<script>
import CommitCommon from './CommitCommon.vue'
import axios from 'axios'

export default {
  components: { CommitCommon },
    name:'CommitSet',
    props:['blog_id'],
    data(){
        return{
            ids: [],
        }
    },methods:{

    },
    watch:{
        blog_id:function(newData){
            let uid = newData
            //axios查找blog底下所有的主评论id，放在ids里
            axios.post("/CommitSet",{
                blog_id: uid
            }).then(res => {
                console.log(res.data);
                this.ids = res.data.data; 
            }).catch(error => {
                console.log("fail to request :" + error);
            })
        }
    }
}
</script>

<style>

</style>
    
