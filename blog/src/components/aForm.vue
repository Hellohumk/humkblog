<template>


  <!-- <form action="/#/updatemsg" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="user_name"><br>
    性别:<select name="gender">
           <option label="男" value="1"></option>
           <option label="女" value="2"></option>
         </select><br>
    头像:<input type="file" name="image"><br>
    <input type="submit" value="提交" @onclick="UpdatePerson">
  </form> -->

  <el-form :model="form" label-width="auto" style="max-width: 600px">
    <el-form-item label="输入用户名">
      <el-input v-model="form.user_name" />
    </el-form-item>
    <el-form-item label="性别">
      <el-select v-model="form.gender" placeholder="please select your zone">
        <el-option label="男" value="1" />
        <el-option label="女" value="2" />
      </el-select>
    </el-form-item>
    <el-form-item label="头像上传">
        <el-upload
          v-model:fileList="fileList"
          action="none"
          multiple
          :limit="1"
          :auto-upload="false"
        >
          <el-button type="primary">Click to upload</el-button>
          <template #tip>
            <div class="el-upload__tip">
              jpg/png files with a size less than 500KB.
            </div>
          </template>
      </el-upload>
    </el-form-item>

  </el-form>
  <button @click="UpdatePerson">点我提交</button>

</template>

<script>
import axios from 'axios'

export default {
    name:'aForm',
    props:[],
    data(){
        return {
          form:{
            user_name:'',
            gender:'',
            fileList:[]
          }

        }
    },methods:{
      UpdatePerson:function(){
        let file = ""
        if(this.fileList !== null && this.fileList !== undefined){//允许用户传空文件
          file = this.fileList.length ? this.fileList[0].raw : ''
        }
        if(this.form.user_name == null || this.form.user_name == undefined || this.form.user_name == ''){
          alert("用户名不许为空")
          return 0
        }

        
        axios.post("/updateperson",{
          user_name : this.form.user_name,
          gender : this.form.gender,
          image : file
        },{
          headers:{
            'Content-Type':'multipart/form-data'
          },
        }).then(res =>{
          console.log("ok")
          console.log(res.data)
          if(res.data.code==1){
            alert("操作成功")
            this.$parent.closedialog()
          }
          else{
            alert("更改失败，叫hmk起床debug")
          }
        }).catch(err => {
          console.log("fail to update :" + err)
        })
        

      }
    }


}
</script>

<style>

</style>