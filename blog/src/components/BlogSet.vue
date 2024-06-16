<template>
  <!-- <div>{{ this.input }}</div>
  <div>{{ this.pickdate[0] }}</div>
  <div>{{ this.pickdate[1] }}</div> -->
    <span><SearchBlog ref='childRef'></SearchBlog></span>
    <span><el-button type="primary" @click="searchforblog">搜索</el-button></span>


  <!-- 无限滚动框 没做好后面做 -->
    <Blog v-for="blog in showarray" :key="blog.id" :ID="blog.id" :title="blog.blog_topic" :update_time="blog.update_time" :document="blog_text"></Blog>


</template>

<script>
import Blog from '@/components/aBlog.vue';
import SearchBlog from '@/components/searchBlog.vue'





export default {
    name:'BlogSet',
    props: ["blogarray"],
    watch:{
      blogarray:function(newData){
        this.showarray = newData
      }
    },
    components: {
      Blog,
      SearchBlog
    },
    data(){
        return{
          showarray :[],
          input:'',
          pickdate :[],
          inputID :'',
          inputwriter_id:'',

        }
    },methods:{
        // load:function(){
        //     count.value += 2;
        // },
        //接受子组件
        getmessage:function(){
          //整个页面渲染完后才拿数据
          this.$nextTick(() => {
          this.input = this.$refs.childRef.getInput();
          this.inputID = this.$refs.childRef.getInputid();
          this.inputwriter_id =this.$refs.childRef.getInputwriter_id();
          this.pickdate = this.$refs.childRef.getPickdate();
          })

        },
        searchforblog:function(){
          // window.alert("ok");//test
          //前端检索//检索完后的新数据放在showarray数组中
          //先赋值，再一层一层筛选

                  // 这里我们发现this对象和this对象赋值不上去
          let newListData = this.blogarray;
          this.showarray = newListData; 
          //调用函数拿回子组件值
          //getmessage
          this.getmessage();


          //input
          if(this.input != ''){//input 不为空
            let newListData = []; // 用于存放搜索出来数据的新数组
            this.showarray.filter((item) => {
                if (item.blog_topic.indexOf(this.input) !== -1) {
                  newListData.push(item);
                }
              });
              this.showarray = newListData;
          }
          //日期选择
          if(this.pickdate.length!=0){
            window.alert("ok2");//test
            const begin = new Date(this.pickdate[0]);
            const end = new Date(this.pickdate[1]); 
            let newListData = []; // 用于存放搜索出来数据的新数组
            this.showarray.filter((item) => {
              const create_time = new Date(item.create_time);
              if(create_time >= begin && create_time <=end){
                newListData.push(item);
              }
            });
            this.showarray = newListData;
          }
          //username
          if(this.inputwriter_id !=''){
            let newListData = []; // 用于存放搜索出来数据的新数组
            this.showarray.filter((item) => {
              if(item.writer_id == Number(this.inputwriter_id)){

                  newListData.push(item);
                }
              });
              this.showarray = newListData;
          }
          //blog_id
          if(this.inputID != ''){
            let newListData = []; // 用于存放搜索出来数据的新数组
            this.showarray.filter((item) => {
            if(item.id == Number(this.inputID)){
                  newListData.push(item);
                }
              });
              this.showarray = newListData;
          }
          // window.alert("ok3");
        },


  },
};
</script>

<style>
.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
}
.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: var(--el-color-primary-light-9);
  margin: 10px;
  color: var(--el-color-primary);
}
.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}





</style>