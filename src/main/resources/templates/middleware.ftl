<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
    <script src="../static/js/cdn.jsdelivr.net_npm_@element-plus_icons-vue"></script>
<#--    <script src="https://unpkg.com/vue-typed-js@2.0.0/dist/vue-typed-js.umd.min.js"></script>-->

    <script src="../static/js/cdnjs.cloudflare.com_ajax_libs_three.js_r134_three.min.js"></script>
    <script src="../static/js/cdn.jsdelivr.net_npm_vanta_dist_vanta.clouds.min.js"></script>


</head>
<body>
<div id="app2" style=" width: 100vw; height: 100vh;">
    <div id="app" style="width: 100vw; height: 100vh;">
        <div class="common-layout">
            <el-container style="width: 100vw; height: 100vh;">
                <el-header height="100px">
                    <span id="title">{{title}}</span>
                </el-header>

                <el-container style="width: 100vw; height: 100vh;">
                    <el-aside>
                        <el-timeline>
                            <el-timeline-item
                                    v-for="(item, index) in timelineItems"
                                    :key="item.id"
                                    :timestamp="item.timestamp"
                                    placement="top"
                            >
                                <el-card
                                        :class="{ 'selected': selectedItem === item.id }"
                                        @click="selectItem(item.id)"
                                        class="custom-card"
                                >
                                    <h4>{{ item.title }}</h4>
                                    <p>{{ item.description }}</p>
                                </el-card>
                            </el-timeline-item>
                        </el-timeline>
                    </el-aside>

                    <el-main>
                        <el-page-header :icon="null">
                            <template #content>
                                <div class="flex items-center">
                                    <el-avatar
                                            :size="100"
                                            class="mr-3"
                                            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                                    />
                                    <!-- 正确使用方式 -->

                                </div>
                            </template>
                            <template #extra>
                                <div class="flex items-center">
                                    <el-badge is-dot class="item" style="padding-right: 20px">
                                        <el-button  @click="dialogFormVisible = true" class="share-button" :icon="Share" type="primary">
                                            日志
                                        </el-button>
                                    </el-badge>
                                    <el-button>Print</el-button>
                                    <el-button v-if="deployButton" type="primary" class="ml-2" @click="deploy">一键部署</el-button>
                                </div>
                            </template>
                        </el-page-header>

                        <el-row wrap>
                            <el-col v-for="(item, index) in middlewares" :key="item.id" :span="6">
                                <el-card id="card" class="card-item" style="height: 80%">
                                    <div style="height: 100px; width: 100%" class="bottom">
                                        <img
                                                :src=item.imgUrl
                                                class="image"
                                                style="height: 100px; width: 40%"
                                        />
                                        <div style="height: 100px; width: 60%; margin-left: 5%">
                                           <span>{{item.name}}
                                               <el-tag
                                                       class="mx-1"
                                                       effect="light"
                                                       round
                                               >
                                                  {{ item.version }}
                                                </el-tag>
                                           </span>
                                            <div style="margin-top: 10px">
                                                <span v-if="item.hasOwnProperty('userName') && item.userName !== null">
                                               <label>name：<el-input
                                                           v-model="item.userName" style="width: 50%"
                                                           placeholder="Please Input"
                                                   >
                                                </el-input></label>
                                                </span>
                                                <span v-if="item.hasOwnProperty('password') && item.password !== null">
                                                <label>pwd：<el-input v-model="item.password" style="width: 50%"
                                                                     placeholder="Please Input"
                                                    >
                                                </el-input>
                                                </label>
                                                </span>


                                                <span v-if="item.hasOwnProperty('jarPath') && item.jarPath !== null">
                                                <label>jarPath：<el-input v-model="item.jarPath" style="width: 50%"
                                                                     placeholder="Please Input"
                                                    >
                                                </el-input>
                                                </label>
                                                </span>

                                                <span v-if="item.hasOwnProperty('expose') && item.expose !== null">
                                                <label>expose：<el-input v-model="item.expose" style="width: 50%"
                                                                     placeholder="Please Input"
                                                    >
                                                </el-input>
                                                </label>
                                                </span>

                                                <span v-if="item.hasOwnProperty('author') && item.author !== null">
                                                <label>author：<el-input v-model="item.author" style="width: 50%"
                                                                     placeholder="Please Input"
                                                    >
                                                </el-input>
                                                </label>
                                                </span>


                                                <span v-if="item.hasOwnProperty('mysqlServiceHost') && item.mysqlServiceHost !== null">
                                                <label>mysqlServiceHost：<el-input v-model="item.mysqlServiceHost" style="width: 50%"
                                                                        placeholder="Please Input"
                                                    >
                                                </el-input>
                                                </label>
                                                </span>

                                                <span v-if="item.hasOwnProperty('nacosDatabase') && item.nacosDatabase !== null">
                                                <label>nacosDatabase：<el-input v-model="item.nacosDatabase" style="width: 50%"
                                                                                  placeholder="Please Input"
                                                    >
                                                </el-input>
                                                </label>
                                                </span>

                                            </div>
                                        </div>
                                    </div>
                                    <div style="padding: 14px">
                                        <span>{{item.description}}</span>
                                        <div class="bottom">
                                            <time class="time">{{ currentDate }}</time>
                                            <el-button v-if="!deployButton" type="primary" class="ml-2" @click="deployService(item.id)">部署</el-button>
                                            <el-switch v-if="deployButton"
                                                    v-model="item.isSelect"
                                                    class="ml-2"
                                                    inline-prompt
                                                    style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
                                                    active-text="选择"
                                                    inactive-text="取消"
                                                    @change="isSelect"
                                            />

                                        </div>
                                    </div>
                                </el-card>
                            </el-col>
                        </el-row>

                        <el-dialog v-model="dialogFormVisible" title="Shipping address" style="background-color: black;">

                            <div ref="scrollContainer" style="height: 50vh; overflow-y: scroll;" class="scroll-container">
                                <p v-for="(item, index) in ListMessage" :key="item" style="color: white">{{ item }}</p>
                            </div>

                            <template #footer>
                              <span class="dialog-footer">
                                <el-button @click="clear">清屏</el-button>
                                <el-button type="primary" @click="dialogFormVisible = false">
                                  Confirm
                                </el-button>
                              </span>
                            </template>
                        </el-dialog>
                    </el-main>
                </el-container>
            </el-container>
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
            const title = Vue.ref("MiddleWareDeploy | 平台");
            const currentDate = Vue.ref(new Date())
            const selectedItem = Vue.ref(0);
            const middlewares = Vue.ref([]);
            const timelineItems = ${categorys} //直接将 model中的数据赋值 给item
            const dialogFormVisible = Vue.ref(false)
            const formLabelWidth = '140px'
            const context = Vue.ref("ioertiuerioutioeirtieiotu")
            const ListMessage = Vue.reactive([])

            const deployButton = Vue.ref(true)

            const scrollContainer = Vue.ref(null);

            // timeline 被选中时的状态函数
            function selectItem(index) {
                console.log(index)
                if(index === 1){
                    deployButton.value = false;
                }else{
                    deployButton.value = true;
                }


                console.log(deployButton,"----------------")

                selectedItem.value = index;
                axios.get("http://localhost:9999/admin/deploy/" + index)
                    .then(({data}) => {
                        console.log(data.data)
                        middlewares.value = data.data
                    })
                    .catch(function (error) {
                        console.log(error)
                    });
            }

            // 选择中间件
            const isSelect = () => {
                console.log(middlewares.value)
            }

            const clear = () =>{
                ListMessage.splice(0, ListMessage.length);
                console.log("clear",ListMessage)
            }

            Vue.watch(
                ListMessage,
                () => {
                    Vue.nextTick(() => {
                        if (scrollContainer.value) {
                            scrollContainer.value.scrollTop = scrollContainer.value.scrollHeight;
                        }
                    });
                }
            );

            // 部署 中间件
            const deploy = () => {
                dialogFormVisible.value = true
                axios.post('http://localhost:9999/admin/deploy/v2', middlewares.value)
                    .then(({data}) => {
                        console.log(data);
                        // 处理成功响应
                        if (data.code === 200) {

                            // 进行页面跳转
                            // window.location.href = 'http://localhost:9999/admin/middleware';
                        }
                    })
                    .catch(function (error) {
                        // 处理错误响应
                        // ElMessage.error(error)
                    });
            }


            // 部署 服务
            const deployService = (id) => {
                dialogFormVisible.value = true
                for (var middleware in middlewares.value){
                    if(middlewares.value[middleware].id === id){
                        middlewares.value[middleware].isSelect = true
                    }
                }
                axios.post('http://localhost:9999/admin/deploy/service', middlewares.value)
                    .then(({data}) => {
                        console.log(data);
                        // 处理成功响应
                        if (data.code === 200) {

                            // 进行页面跳转
                            // window.location.href = 'http://localhost:9999/admin/middleware';
                        }
                    })
                    .catch(function (error) {
                        // 处理错误响应
                        // ElMessage.error(error)
                    });
            }

            const websocket = () => {
                let socket = new WebSocket("ws://localhost:9998/");
                socket.onopen = function () {
                    console.log('WebSocket连接已打开');
                    // 在连接打开后发送一段消息
                    socket.send(JSON.stringify({
                        "code": "10001",
                        "nickname": "zhangsan"
                    }));
                };

                socket.onmessage = function (event) {
                    console.log('收到服务器消息: ' + event.data);
                    // 处理从服务器接收到的消息
                    ListMessage.push(event.data)
                };

                socket.onclose = function () {
                    console.log('WebSocket连接已关闭');
                };
            }


            Vue.onMounted(() => {
                // 在组件挂载完成后执行的代码
                middlewares.value = ${middlewares}
                console.log(middlewares.value)
                websocket()
            });

            return {
                clear,
                dialogFormVisible,
                title,
                timelineItems,
                selectedItem,
                currentDate,
                middlewares,
                deployService,
                selectItem,
                isSelect,
                deploy,
                websocket,
                context,
                formLabelWidth,
                ListMessage,
                scrollContainer,
                deployButton
            }
        },


    });

    app.use(ElementPlus)
    app.mount('#app');
</script>

<style>

    .scroll-container::-webkit-scrollbar {
        width: 8px;
        background-color: black;
    }

    .scroll-container::-webkit-scrollbar-thumb {
        background-color: #333;
    }

    .scroll-container::-webkit-scrollbar-track {
        background-color: black;
    }

    .card-item:hover {
        border-color: blue;
        /*background-color: skyblue;*/
    }

    #card {
        margin: 10px;
        height: 200px;
        width: 300px;
        background-color: transparent;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
    }

    .time {
        font-size: 12px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .button {
        padding: 0;
        min-height: auto;
    }

    .image {
        width: 100%;
        display: block;
    }

    .input-with-select .el-input-group__prepend {
        background-color: var(--el-fill-color-blank);
    }

    .custom-card {
        background-color: transparent !important;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
    }

    .selected {
        background-color: skyblue !important;
    }

    #title {
        position: absolute;
        top: 2%;
        margin-left: 10%;
        font-size: 32px;
        font-family: Algerian;
    }

    html, body {
        margin: 0;
        padding: 0;
        height: 100%;
        width: 100%;
    }

    .scrollbar-demo-item {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 50px;
        margin: 10px;
        text-align: center;
        border-radius: 4px;
        background: var(--el-color-primary-light-9);
        color: var(--el-color-primary);
    }


    .el-button--text {
        margin-right: 15px;
    }
    .el-select {
        width: 300px;
    }
    .el-input {
        width: 300px;
    }
    .dialog-footer button:first-child {
        margin-right: 10px;
    }
</style>
</body>
</html>
