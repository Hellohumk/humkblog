<template>
    <!-- <span>
        <el-avatar :src="image"  :size="1" shape="square"> 头像 </el-avatar>
    </span>
    <span>
        <div>
            <el-table :data="user" style="width: 100%">
                <el-table-column prop="username" label="用户名" width="180" > </el-table-column>
                <el-table-column prop="exp" label="等级" width="180" > </el-table-column>
                <el-table-column prop="user_blog_sum" label="您发布的数目" > </el-table-column>
            </el-table>
        </div>
    </span> -->
    <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <el-avatar :src="this.user.image"  :size="100" shape="square"> 头像 </el-avatar>
    </el-aside>
      <el-main>
        <el-row>
            <el-col :span="10"><div class="grid-content ep-bg-purple" >
                <!-- <el-table :data="user" style="width: 100%">
                    <el-table-column prop="username" label="用户名" width="180" > </el-table-column>
                    <el-table-column prop="exp" label="等级" width="180" > </el-table-column>
                    <el-table-column prop="user_blog_sum" label="ta发布的数目" > </el-table-column>
                </el-table> -->
                <span>{{ user.user_name }}</span><br>
                <span>经验{{ user.exp }}</span><br>
                <span>发布数目{{ user.user_blog_sum }}</span><br>
            </div></el-col>
         </el-row>
      </el-main>
    </el-container>
  </div>


</template>

<script>
import axios from 'axios'

export default {
    name:'PersonShow',
    props: ['id'],
    data(){
        return{
            user :
            {

            }
        }
    },methods:{

    },
    watch:{
        id:function(newData){
            let blog_id = newData
            axios.get("person",{
                params:{
                    id: blog_id
                }
            }).then(res => {
                console.log(res.data);
                this.user = res.data.data; 
            }).catch(error => {
                console.log("fail to request :" + error);
            })
        }
    }
}
</script>

<style>

</style>