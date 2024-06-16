<template>
    <!-- 折叠面板展示 -->
    <div class="demo-collapse">
    <el-collapse @change="handleChange">
        <!-- title -->
      <Commit :id="father_id"></Commit>
      <button @click="clicktosubcommit">怼一下？</button>
      <el-input
        v-model="inputsubcommit"
        style="width: 240px"
        placeholder="Please input"
        clearable
        />
      <el-collapse-item name="1">
        <!-- 正文 -->
            <Commit v-for="id in ids" :key="id" :id="id"></Commit>
       </el-collapse-item>
      </el-collapse>
    </div>
    
</template>

<script>
import Commit from '@/components/aCommit.vue'
import axios from 'axios'


// const activeNames = ref(['1'])


export default {
    name:'CommitCommon',
    props:['father_id'],//这个里面是当前blog底下的一条主评论id
    components:{
        Commit
    },
    data(){
        return{
            ids:[],//所有属于该commit下的子评论id
            inputsubcommit:''
        }
    },methods:{
        handleChange:function(val){
            console.log(val)
        },
        clicktosubcommit:function(){
            // axios update
            axios.post("/commit",{
                father_id: this.father_id,
                commit_text: this.inputsubcommit
            }).then(res => {
                window.alert("ok")
                console.log(res)
                //清空inputcommit
                this.inputsubcommit = ''
                window.alert("评论成功") 
            }).catch(error => {
                console.log("fail to request :" + error);
            })
        }
    },mounted(){
        //axios拿到对应commit底下的id们(依据father——id)
        axios.post("/blog/CommitCommon",{
            father_id: this.father_id
        }).then(res => {
            console.log(res.data);
            this.ids = res.data.data; 
        }).catch(error => {
            console.log("fail to request :" + error);
        })
    }
}
</script>

<style>

</style>