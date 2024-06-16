import Mock from 'mockjs'

Mock.setup({
    timeout: 400
})

Mock.mock('http://localhost:8080/blog/Home','get',{
    "list|1-10": [
        {
            "id|1-50" : 1,
            "blog_topic" : '@cname',
            "update_time" : '@date',
            "create_time" : '@date',//不显示，但要给查询用
            "writer_id|1-50": 1
        }
    ],
});

Mock.mock(RegExp('http://localhost:8080/blog/Page' +".*"),'get',{
    "blog":{
        "writter_id|1-10":1,
        "blog_text":"@image('200x100','#ffcc33','#FFF','png','Mock!')",//文章
        "blog_topic":'@cname',
        "create_time":'@date',
        "update_time":'@date',
        "likes_sum|1-50":10,
        "commit_sum|1-10":1,
    },
});

// const Random = Mock.Random

//personshow
//这里正则表达式也拦住了personal/Blog,6666666666666666666666666666 加个/就好了
Mock.mock(RegExp('http://localhost:8080/person/' + ".*"),'get',{
    "user":{
        "user_name" :'@cname',
        'image' :"@image('200x100','#ffcc33','#FFF','png','Mock!')",
        'exp|1-1000' : 200,
        'user_blog_sum|1-10' : 1,
    }
});

// //commit//commitcommon
// Mock.mock('http://localhost:8080/blog/Page',function (options) {
//     if(options.body === '"[father_id"]'){
//         return{
//             "ids|1-10":[
//                 () => Random.integer(1,50)
//             ]
//         }
//     }else if(options.body === '["id"]'){
//         return{
//             "ids|1-10":[
//                 () => Random.integer(1,50)
//             ]
//         }
//     }


// });

//commit



//acommit
Mock.mock(RegExp('http://localhost:8080/commit' + ".*"),'get',{
    "commit":{
        'commiter_id|1-10':1,
        'commit_text':'@sentence()',
        'create_time':'@date',
    }
});

//personalHome//jwt


//updateperson
Mock.mock(RegExp('http://localhost:8080/commit' + ".*"),'put',{
    "code":"200",
    "msg":"success",
})
//delete personal.blog


//MOCK测试完毕

//差jwt
Mock.mock(RegExp('http://localhost:8080/personal/Home' + ".*"),'get',{
    "user":{
        "user_name" :'@cname',
        'image' :"@image('200x100','#ffcc33','#FFF','png','Mock!')",
        'exp|1-1000' : 200,
        'user_blog_sum|1-10' : 1,
    }
});

