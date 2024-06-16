<template>
    <PersonShow :id="commit.commiter_id"></PersonShow>
    <el-text>{{ commit.commit_text }}</el-text>
    <el-text>------时间：{{ commit.create_time }}</el-text><br>
    
</template>

<script>
import PersonShow from '@/components/personShow.vue'
import axios from 'axios'

export default {
    name:'aCommit',
    props:['id'],//评论id
    watch:{
        id:
        {
            handler(newData){
                this.commitid = newData
            },
            immediate:true,
            deep:true,
        },

    },

    components:{
        PersonShow
    },
    data(){
        return{
            commit:{

            },
            commitid:''
        }
    },methods:{

    },mounted(){
            //axios拿commit
            // alert(this.commitid)
            axios.get("/commit",{
                params:{
                    id: this.commitid
                }
            }).then(res => {
                console.log(res.data);
                this.commit = res.data.data; 
            }).catch(error => {
                console.log("fail to request :" + error);
            })
    }
}

</script>

<style>

</style>