import { defineStore } from 'pinia'
import { ref } from 'vue'

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