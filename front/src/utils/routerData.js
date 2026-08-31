export default [
    {
        path: '/', component: 'main', children: [
            // {
            //     path: '/index',
            //     component: 'main/index'
            // },
            {
                path: '/houselist',
                name: 'HouseList',
                component: "admin/houseList"
            },
            {
                path: '/addhouse',
                name: 'Addhouse',
                component: "admin/addhouse", //添加房屋
            },
        ],
    },
    // {
    //     path: '/:msg*',
    //     component: "404/404",
    // }
]