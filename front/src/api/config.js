import axios from "axios";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";

const router = useRouter()

const Service = axios.create({
    // baseURL: 'http://192.168.245.152:8080/',
    baseURL: 'http://localhost:8081/',
    timeout: 100000
})
// 添加请求拦截器
Service.interceptors.request.use((config) => {
    console.log('请求拦截器');
    // 在发送请求之前做些什么
    let token = window.sessionStorage.getItem('token')
    console.log("token==>" + token);
    if (token) {
        //1.2 注意：给请求头里面添加token ——————  后台判断就是取的这个请求头
        config.headers['token'] = token;
    }
    // console.log("===========config==========");
    // console.log(config);
    return config
},
    (error) => {
        // 对请求错误做些什么
        // console.log("============error=========");
        // console.log(error)
        return Promise.reject(error)
    }
);

// 添加响应拦截器
Service.interceptors.response.use((response) => {
    console.log('响应拦截器');
    // 对响应数据做点什么
    console.log(response);
    let data = response.data
    if (!data.success && data.msg === "NoUser")
        router.push('/login')
    return response;
},
    (error) => {
        if (error.code == "ERR_BAD_RESPONSE") {
            ElMessage.error("有点小问题");
        } else {
            ElMessage.error(error)
        }
        console.log(error);
        // 处理响应错误
        return Promise.reject(error);
    },
);

export default Service;