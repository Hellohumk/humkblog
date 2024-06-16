<template>
    <commit :id="this.commitid"></commit>
    <el-button type="primary" :icon="Delete" @click="DeleteCommit" >删</el-button>
    <el-button type="primary" :icon="Edit" @click="UpdateCommit" >改</el-button>
    </template>
<script>
// import { Delete } from '@element-plus/icons-vue/dist/types';
// import { Edit } from '@element-plus/icons-vue/dist/types';
import Commit from '@/components/aCommit.vue'
import axios from 'axios'

export default {
    name:'PersonCommit',
    props:['id'],
    watch:{
        id:
        {
            handler(newData){
                this.commitid = newData
            },
            immediate:true,
        },
    },
    components:{
        Commit
    },

    data(){
        return{
            commitid:''
        }
    },methods:{
            DeleteCommit:function(){
                //axios//delete
                axios.delete("/commit",{
                    params:{
                        id: this.commitid
                    }
                }).then(res => {
                    console.log(res.data)
                    if(res.data.code == 1){
                        alert("删除成功")
                    }
                    else{
                        if(res.data.msg == "Not access"){
                            alert("你不能删除别人的！")
                        }else{
                            alert("系统错误")
                        }

                    }
                }).catch(error => {
                    console.log("fail to request :" + error);
                })
            },
            UpdateCommit:function(){
                // //axios//update
                // axios.put("/commit",{
                // params:{
                //     id: this.id
                // }
                // }).then(res => {
                //     console.log(res);
                // }).catch(error => {
                //     console.log("fail to request :" + error);
                // })
                window.alert("怼出去的话就别改了把，删掉是尊严")
            }
    }
}
</script>

<style>

</style>