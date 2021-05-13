/* eslint no-unused-vars:0 */
import Axios from 'axios';

import { notification } from 'antd';
import { createHashHistory } from 'history'; // 如果是hash路由
import { createBrowserHistory } from 'history'; // 如果是history路由
//
const url = 'http://localhost:8080';
const QN_URL = '';
let isShowingNotify = false;

// var CancelToken = Axios.CancelToken;
// var cancel;

/* 服务器ip地址 */
// Axios.defaults.timeout = 5000; //响应时间
Axios.defaults.baseURL = url; //接口地址
Axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'; //设置请求头
Axios.defaults.headers.common['jwtToken'] = localStorage.getItem('jwtToken');
Axios.defaults.headers.common['userId'] = localStorage.getItem('userId');
Axios.defaults.headers.common['endtime'] = localStorage.getItem('endtime');//时光机时间
Axios.defaults.retry = 2; //重试次数
Axios.defaults.retryDelay = 1000; // 重试延时
Axios.defaults.shouldRetry = (error) => true; //重试条件，默认只要是错误都需要重试
// Axios.defaults.cancelToken = new CancelToken(function executor (c) {
//     // executor 函数接收一个 cancel 函数作为参数
//     cancel = c
//     window.addEventListener('hashchange', () => {
//         cancel()
//         window.location.reload()
//         console.log('取消了吗',Axios.defaults)
//     })
// })
localStorage.setItem('url', url);

// //请求拦截器

const TOKEN_ERR_MSG = ['TOKEN错误或过期', '已在其他地方登陆'];

// Axios.interceptors.request.use(
//     (config) => {
//         if (config.method === 'post') {
//             //先对数据进行转换，把对象、数组等数据转换为json字符串
//             config.data = JSON.stringify(config.data);
//         }
//         return config;
//     },
//     (error) => {
//         Notification.error({
//             title: '错误',
//             message: 'post或get请求代码有误，请检查'
//         });
//         return Promise.reject(error);
//     }
// );
// 响应拦截器
// Axios.interceptors.response.use(
//     (res) => {
//         if (res.data.event === 1) {
//             if (TOKEN_ERR_MSG.includes(res.data.msg)) {
//                 localStorage.jwtToken = '';
//                 handleNotify('用户身份失效，请重新登陆');
//                 createHashHistory().replace('/');
//             }
//         }
//         if (res.status !== 200) {
//             if (res.status >= 500) {
//                 handleNotify('系统异常，请检查服务端代码');
//             }
//             return Promise.reject(res);
//         }
//         return res.data;
//     },
//     (error) => {
//         handleNotify('系统异常，请稍后重试');
//         return Promise.reject(error);
//     }
// );

function handleNotify(description) {
    if (!isShowingNotify) {
        isShowingNotify = true;
        notification.open({
            message: '系统提示',
            description,
            onClose: () => {
                isShowingNotify = false;
            }
        });
        // console.log(error);
        // return Promise.reject(error);
    }
}

function showNotification(type, description) {
    if (type === 'error') var title = '错误'
    else if (type === 'success')  title = '成功';
    else if (type === 'warning')  title = '警告';
    else if (type === 'open')  title = '系统提示';
    notification[type]({
        message: title,
        description: description
    });
}

// Axios.interceptors.response.use(undefined, (err) => {
//   var config = err.config;
//   // 判断是否配置了重试
//   if(!config || !config.retry) return Promise.reject(err);
//
//   if(!config.shouldRetry || typeof config.shouldRetry != 'function') {
//     return Promise.reject(err);
//   }
//
//   //判断是否满足重试条件
//   if(!config.shouldRetry(err)) {
//     return Promise.reject(err);
//   }
//
//   // 设置重置次数，默认为0
//   config.__retryCount = config.__retryCount || 0;
//
//   // 判断是否超过了重试次数
//
//
//   //重试次数自增
//   config.__retryCount += 1;
//   console.log('config.__retryCount ' + config.__retryCount);
//   //延时处理
//   var backoff = new Promise(function(resolve) {
//     setTimeout(function() {
//       resolve();
//     }, config.retryDelay || 1);
//   });
//
//   //重新发起Axios请求
//   return backoff.then(function() {
//     return Axios(config);
//   });
// });
function get(url, params = {}) {
    Axios.defaults.headers.common['jwtToken'] = localStorage.getItem(
        'jwtToken'
    );
    Axios.defaults.headers.common['userId'] = localStorage.getItem('userId');
    Axios.defaults.headers.common['endtime'] = localStorage.getItem('endtime');//时光机时间
    return new Promise((reslove, reject) => {
        Axios.get(url, {
            params: params
        }).then(
            (response) => {
                reslove(response);
            },
            (err) => {
                reject(err);
            }
        );
    });
}

// async function post(url ,data = {}){
//  return Axios.post(url,data)
//    .then(res=>{
//      return res
//    }).catch(err => {
//      console.log("error")
//    })
// }
function post(url, data = {}) {
    Axios.defaults.headers.common['jwtToken'] = localStorage.getItem(
        'jwtToken'
    );
    Axios.defaults.headers.common['userId'] = localStorage.getItem('userId');
    Axios.defaults.headers.common['endtime'] = localStorage.getItem('endtime');//时光机时间
    return new Promise((reslove, reject) => {
        Axios.post(url, data)
            .then((response) => {
                reslove(response);
            })
            .catch((error) => {
                reject(error);
            });
    });
}
/*
    使用方法:
    在组件的任意位置都可以调用
     get： this.$get(url[,params])
                .then(res=>{
                    .....
                })
     post： this.$post(url[,params])
                .then(res=>{
                    .....
                })

       tips:url只需写特定的路径即可，不需要完整的url，如 http://123.45.67.12:8080/methods/getData
       可写为 /methods/getData
       params代码接口需要的参数，类型是对象（已提前转为Json,无需手动再转)
 */
function upload(fileUri, fileNAME, httpuri) {
    alert(fileUri, fileNAME, httpuri);
    let formData = new FormData();
    let file = {
        uri: fileUri,
        type: 'multipart/form-data',
        name: fileNAME
    };
    formData.append('file', file);
    let config = {
        Accept: 'Application/json',
        'Content-Type': 'multipart/form-data'
    };
    return new Promise((reslove, reject) => {
        Axios.post(httpuri, formData, config)
            .then((response) => {
                reslove(response);
            })
            .catch((error) => {
                reject(error);
            });
    });
}

/*async uploadFile () {
    var config = {
      onUploadProgress: (progressEvent) => {          
        let percentCompleted = Math.round((progressEvent.loaded * 100) / progressEvent.total) 
      }
    }      
    let formData = new FormData()      
    let form = document.getElementById('headImg')      
    let file = form.files[0]  
    formData.append('file', file)      
    let Url = window.URL || window.webkitURL      
    var imgURL = Url.createObjectURL(file)      
    this.setState({        
        headHref: imgURL      
    })      
    let res = await axios.post('/user/head/upload', formData, config)
        .then(res => {        
            this.ticket = res.data.image        
            // this.state.upData[tag] = logoTicket        
            // this.checkIfCanCommit()      
        }).catch(err => { console.error(err) })      
        axios.post('user/head/update', {        
          head_file: res.data.data.head_file 
        })
}*/

export { get, post, upload, showNotification };
