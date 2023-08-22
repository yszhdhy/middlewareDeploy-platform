<!DOCTYPE html>
<html>
<head>
    <!-- Import style -->
    <link
            rel="stylesheet"
            href="../static/css/cdn.jsdelivr.net_npm_element-plus_dist_index.css"
    />
    <!-- Import Vue 3 -->
    <script src="../static/js/cdn.jsdelivr.net_npm_vue@3"></script>
    <!-- Import component library -->
    <script src="../static/js/cdn.jsdelivr.net_npm_element-plus"></script>
<#--    <script src="//cdn.jsdelivr.net/npm/element-plus@latest/dist/index.full.js"></script>-->
    <#--    引入 axios-->
    <script src="../static/js/cdn.jsdelivr.net_npm_axios_dist_axios.min.js"></script>

    <script src="../static/js/cdnjs.cloudflare.com_ajax_libs_three.js_r134_three.min.js"></script>
    <script src="../static/js/cdn.jsdelivr.net_npm_vanta_dist_vanta.clouds.min.js"></script>

</head>
<body style=" height: 100% ; width: 100%; margin: 0;padding: 0">
<div id="app2"  style=" width: 100vw; height: 100vh;">
    <div id="app">
        <span id="title">{{title}}</span>

        <div id="form">
            <el-form :model="form" label-width="60px">
                <el-form-item label="host">
                    <el-input v-model="form.host" />
                </el-form-item>
                <el-form-item label="port">
                    <el-input-number
                            v-model="form.port"
                            class="mx-4"
                            :min="1"
                            :max="64500"
                            controls-position="right"
                    />
                </el-form-item>
                <el-form-item label="userName">
                    <el-input v-model="form.userName" />
                </el-form-item>
                <el-form-item label="password">
                    <el-input v-model="form.password"/>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="onSubmit">connect</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</div>
<script>
    VANTA.CLOUDS({
        el: "#app2",
        mouseControls: true,
        touchControls: true,
        gyroControls: false,
        minHeight: 200.00,
        minWidth: 200.00
    })
    const app = Vue.createApp({
        setup() {

            const title = Vue.ref("MiddleWareDeploy");
            const form = Vue.reactive({
                host:'',
                port:22,
                userName:'',
                password:'',
            })
            const onSubmit = () => {
                console.log("开始网络请求")
                console.log(form)

                axios.post('http://localhost:9999/admin/serverDisposition', form)
                    .then( ({data}) =>{
                        console.log(data);
                        // 处理成功响应
                        if(data.code === 200){
                            // ElMessage({
                            //     message: data.data,
                            //     type: 'success',
                            // })
                            // 进行页面跳转
                            window.location.href = 'http://localhost:9999/admin/middleware';
                        }
                    })
                    .catch(function (error) {
                        // 处理错误响应
                        // ElMessage.error(error)
                    });
            }
            return{
                title,
                form,
                onSubmit
            }
        }
    });

    app.use(ElementPlus)
    app.mount('#app');
</script>


<style>
    html, body{
        height: 100%;
        width: 100%;
    }
    #title{
        font-size: 48px;
        font-family: Algerian;
        position: fixed;
        top: 40%;
        left: 13%;
    }
    #form{
        height: 500px;
        width: 500px;
        position: fixed;
        top: 50%;
        left: 13%;
    }
</style>

</body>
</html>
