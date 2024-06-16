<template>
  <div style="border: 1px solid #ccc">
    <Toolbar
      style="border-bottom: 1px solid #ccc"
      :editor="editorRef"
      :defaultConfig="toolbarConfig"
      :mode="mode"
    />
    <Editor
      style="height: 500px; overflow-y: hidden;"
      v-model="valueHtml"
      :defaultConfig="editorConfig"
      :mode="mode"
      @onCreated="handleCreated"
    />
  </div>
</template>
   
<script>
import '@wangeditor/editor/dist/css/style.css' // 引入 css
import axios from 'axios'

import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

export default {
  name:'aEditor',
  props:[],
  components: { Editor, Toolbar },
  setup() {
    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()

    // 内容 HTML
    const valueHtml = ref('<p>hello</p>')

    // 模拟 ajax 异步获取内容
    onMounted(() => {
        setTimeout(() => {
            valueHtml.value = '<p>say something?</p>'
        }, 1500)
    })

    const toolbarConfig = {}
    const editorConfig = { 
      placeholder: '请输入内容...',
      MENU_CONF: {}     
    }

    editorConfig.MENU_CONF['uploadImage'] = {
    //  server: 'http://localhost:8081/upload',
     filedName:'file',
    //  meta: {
    //    Authorization:localStorage.getItem('token'),


    //   },
      //oss上传自定义
      async customUpload(file,insertFn){
        axios.post('/upload',{
            file: file
        },{
          headers:{
            'Content-Type':'multipart/form-data'
          }
        }).then(res => {
          console.log(res.data)
          if(res.data.code==1){
            //success
            insertFn(res.data.data)//upload url
          }
        })
      },
      onSuccess(file,res){
        
        console.log("上传成功file："+file)
        console.log("保存oss："+ res.data.data)

      }
    }

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
        const editor = editorRef.value
        if (editor == null) return
        editor.destroy()
    })

    const handleCreated = (editor) => {
      editorRef.value = editor // 记录 editor 实例，重要！
    }

    const getvaluehtml = () => {
      return valueHtml
    }



    return {
      editorRef,
      valueHtml,
      mode: 'default', // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated,
      getvaluehtml
    };
  }
}
</script>