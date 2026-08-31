import { defineStore } from 'pinia'
import { ref } from 'vue'
// 第一个参数是你的应用中 Store 的唯一 ID。


//存储 表格数据 
export const adminStore = defineStore('admin',
    // {
    // state: () => ({ rowData: {} }),
    // getters: {
    // },
    // actions: {  
    //     changeRowData(plyload) {
    //         this.rowData = plyload
    //     },
    //     clearHouse() {
    //         this.rowData = {}
    //     }
    // },
    // vue3 
    () => {
        const rowData = ref({})
        function changeRowData(plyload) {
            rowData.value = plyload
        }
        function clearHouse() {
            rowData.value = {}
        }

        return { rowData, changeRowData, clearHouse }
    })
    
export const userStore = defineStore('user',
    () => {
        const rowData = ref({})
        function changeRowData(plyload) {
            rowData.value = plyload
        }
        function clearHouse() {
            rowData.value = {}
        }

        return { rowData, changeRowData, clearHouse }
    })

//存储 个人信息
export const adminInfo = defineStore('admininfo',
    () => {
        const Info = ref({})
        function changeInfo(plyload) {
            Info.value = plyload
        }
        function clearInfo() {
            Info.value = {}
        }

        return { Info, changeInfo, clearInfo }
    })
export const userInfo = defineStore('userinfo',
    () => {
        const Info = ref({})
        function changeInfo(plyload) {
            Info.value = plyload
        }
        function clearInfo() {
            Info.value = {}
        }

        return { Info, changeInfo, clearInfo }
    })