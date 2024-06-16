import axios from 'axios'


//request filter前置拦截器，让所有请求都带上token
axios.interceptors.request.use((config) => {
    let authorization = localStorage.getItem("token");
    if (authorization) {
      config.headers['Authorization'] = authorization;
    }
      return config
  }),error => {
      Promise.reject(error);
  }
  
  //response filter后置拦截器，处理被拦后行为
  axios.interceptors.response.use(result => {
    //   let data = result.data;
    //   if(data.==0){
    //       console.log("axios.response ok!")
    //       router.push("/login")
    //   }
      // alert("没有登录？")
      if(result.data.code==0 && result.data.msg=='NOT_LOGIN'){
        alert('密码或用户名有误')
        console.log("拦截成功")
        window.location = 'http://101.42.41.45/#/login'
      }
      return result;
  }),error => {
      Promise.reject(error);
  }



