<template>
    <h1>这里是我创建的博客:</h1>
    <el-container>
      <el-header>
        <!-- 写一个 -->
        <el-button type="primary" @click="dialogVisible1 = true">写一个！</el-button>
            <el-dialog title="表单弹框" v-model="dialogVisible1" width="100%">
                <!-- <form action="/createblog" method="post" enctype="multipart/form-data">
                    标题:<input type="text" name="blog_topic"><br>
                    正文:<input type="file" name="blog_text"><br>
                    <input type="submit" value="提交">
                </form> -->
                <el-form :inline="true" :model="form" class="demo-form-inline" >
                    <el-form-item label="输入标题">
                        <el-input v-model="form.blog_topic" placeholder="输入标题" clearable />
                    </el-form-item>
                    <el-form-item>
                        <!-- 富文本编辑器 -->
                        <div>
                            <Editor ref="editor"></Editor>
                        </div>
                    </el-form-item>
                </el-form>
                <button @click="CreateBlog">点我提交</button>
            </el-dialog>
        <!-- 删除一个博客 -->
        <el-button type="tdanger" @click="dialogVisible = true">删除一个博客</el-button>
            <el-dialog title="表单弹框" v-model="dialogVisible" width="30%">
                <el-input v-model="deleteid" style="width: 120px" placeholder="Please input" />
                <!-- 气泡框 -->
                <el-popconfirm title="Are you sure to delete this?" @confirm="onclickdelete">
                    <template #reference>
                        <el-button type="danger">GO!</el-button>
                    </template>
                </el-popconfirm>
            </el-dialog>
        <!-- 修改博客 -->
        <el-button type="info" @click="dialogVisible2 = true">修改博客</el-button>
            <el-dialog title="表单弹框" v-model="dialogVisible2" width="100%" >
                <el-form :inline="true" :model="form" class="demo-form-inline">
                    <el-form-item label="输入id">
                        <el-input v-model="form.id" placeholder="输入id" clearable />
                    </el-form-item>
                    <el-form-item label="输入标题">
                        <el-input v-model="form.blog_topic" placeholder="输入标题" clearable />
                    </el-form-item>
                    <el-form-item>
                        <!-- 富文本编辑器 -->
                        <div>
                            <Editor ref="editor"></Editor>
                        </div>
                    </el-form-item>
                </el-form>
                <button @click="UpdateBlog">点我提交</button>
            </el-dialog>
      </el-header>
      <el-main>
        <BlogSet :blogarray="blogarray"></BlogSet>
      </el-main>
    </el-container>
    

    
</template>

<script>
import axios from 'axios'
import BlogSet from '@/components/BlogSet.vue'

import Editor from '@/components/aEditor.vue'


export default {
  components: {
      BlogSet,
      Editor
    },
    data(){
        return{
            form:{
                id:'',
                blog_topic:''
            },
            blogarray: [],
            //这个是serach拿回来的信息
            input:'',
            dialogVisible: false,
            dialogVisible1: false,
            dialogVisible2: false,
            deleteid:'',


        }
    },methods:{
        onclickdelete:function(){
            //axios by deleteid
            axios.delete('personal/Blog',{
                params:{
                    id: this.deleteid
                }

            }).then(res => {
                this.dialogVisible = false
                console.log(res.data)
                // this.blogarray = res.data.data
                if(res.data.code==0){
                    if(res.data.msg == "Not access"){
                        alert("你不能更新别人的！")
                    }else{
                        alert("系统错误")
                    }
                }
            }).catch(error => {
                console.log("FAIL TO REQUEST: " + error)
            })
        },
        UpdateBlog:function(){
            var blogtext = this.$refs.editor.valueHtml
            if(blogtext == '<p><br></p>'){
                alert("内容不可以为空")
                return 0
            }
            if(this.form.blog_topic == ''){
                alert("标题不可以为空")
                return 0
            }
            if(this.form.id == ''){
                alert("id不可以为空")
                return 0
            }
            axios.post('updateblog',{
                id:this.form.id,
                blog_topic:this.form.blog_topic,
                //blog_text
                blog_text:blogtext
            }).then(res => {
                this.dialogVisible2=false
                console.log(res.data)
                if(res.data.code==0){
                    if(res.data.msg == "Not access"){
                        alert("你不能更新别人的！")
                    }else{
                        alert("系统错误")
                    }
                }
            }).catch(err => {
                console.log("fail to request: " + err)
            })
        },
        CreateBlog:function(){
            var blogtext = this.$refs.editor.valueHtml
            if(blogtext == '<p><br></p>'){
                alert("内容不可以为空")
                return 0
            }
            if(this.form.blog_topic == ''){
                alert("标题不可以为空")
                return 0
            }
            // alert(blogtext)
            // alert(this.form.blog_topic)
            axios.post('createblog',{

                    blog_topic:this.form.blog_topic,
                    //blog_text
                    blog_text:blogtext

            },{
            headers:{
                'Content-Type':'multipart/form-data'
            },
            }).then(res => {
                this.dialogVisible1=false
                alert("提交成功")

                console.log(res.data)
            }).catch(err => {
                console.log("fail to request: " + err)
            })
        },
    },
    mounted() {
       
        //axios
        axios.get('/personal/Blog').then(res => {
            console.log(res.data)
            this.blogarray = res.data.data
        }).catch(error => {
            console.log("FAIL TO REQUEST: " + error)
        })

    },
    unmounted() {

    },
};
</script>

<style>


</style>