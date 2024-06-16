<template>
  <el-row>
    <el-col :span="24">
        <div class="grid-content ep-bg-purple-dark">
            <span>Search topic:</span>  
            <span><el-input v-model="input" style="width: 500px" placeholder="Please input" /></span>
        </div>
    </el-col>
  </el-row>

  <el-row>
    <el-col :span="8">
        <div class="grid-content ep-bg-purple" >
            <div class="demo-date-picker">
                <div class="block">
                    <span class="demonstration">按照日期检索：</span>
                    <el-date-picker
                    v-model="pickdate"
                    type="daterange"
                    unlink-panels
                    range-separator="To"
                    start-placeholder="Start date"
                    end-placeholder="End date"
                    :shortcuts="shortcuts"
                    :size="size"
                 />
                </div>
            </div>
        </div>
    </el-col>
    <el-col :span="8">
        <div class="grid-content ep-bg-purple-light">
            <div>-             -</div>
            <div>查找目标blogid</div>
            <el-input
                v-model="inputID"
                style="width: 240px"
                placeholder="在这里输入Blog的ID"
                clearable
            />
        </div>
    </el-col>
    <el-col :span="8">
        <div class="grid-content ep-bg-purple">
            <div>-             -</div>
            <div>查找目标用户id</div>
            <el-input
                v-model="inputwriter_id"
                style="width: 240px"
                placeholder="在这里输入用户名"
                clearable
            />
        </div>
    </el-col>
  </el-row>
  <!-- 日期选择 -->










</template>

<script>
//wait for listen


export default {
  name:'SearchBlog',
  props:[],
    data(){
        return{
            input:'',
            inputID:'',//input ID
          //date picker
          pickdate : [],//拿到的日期
          inputwriter_id :'',
          shortcuts : [
  {
    text: 'Last week',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
      return [start, end]
    },
  },
  {
    text: 'Last month',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
      return [start, end]
    },
  },
  {
    text: 'Last 3 months',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
      return [start, end]
    },
  },
]
        }
    },methods:{
        getInput(){
          return this.input;
        },
        getPickdate(){
          return this.pickdate;
        },
        getInputid(){
          return this.inputID;
        },
        getInputwriter_id(){
          return this.inputwriter_id;
        }
    },created:function(){
        // this.$emit('listentochild1',this.input)
        // this.$emit('listentochild2',this.pickdate)
        // this.$emit('listentochild3',this.inputID)
        // this.$emit('listentochild4',this.inputusername)
    }

}
</script>

<style>
/* 日期 */
.demo-date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}

.demo-date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}

.demo-date-picker .block:last-child {
  border-right: none;
}

.demo-date-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}

.container{
    display: flex;
}


.box{
    flex :1;
}
</style>