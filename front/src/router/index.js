import { createRouter, createWebHistory } from 'vue-router'
import main from '../components/admin/main'
import index from '../components/main/index'
import umain from '../components/zuke/main'

const routes = [
  {
    path: '/',
    component: main,
    redirect: '/index',
    children: [{
      path: '/index',
      component: index
    },
    {
      path: '/houselist',
      name: 'HouseList',
      component: () => import("../components/admin/houseList.vue"),  //房屋列表
    }, {
      path: '/edit',
      component: () => import("../components/admin/edithouse.vue")
    },
    {
      path: '/addhouse',
      name: 'Addhouse',
      component: () => import("../components/admin/addhouse.vue"), //添加房屋
    },
    {
      path: '/zulist',
      name: 'Zulist',
      component: () => import("../components/admin/zulist.vue") //在租列表
    }, {
      path: '/hetong',
      name: 'Hetong',
      component: () => import("../components/admin/hetong.vue") //查看合同
    },
    {
      path: '/checkout',
      name: 'Checkout',
      component: () => import("../components/admin/checkout.vue") //已退租列表
    },
    {
      path: '/applylist',
      name: 'Applylist',
      component: () => import("../components/admin/applylist.vue") //看房申请
    },
    {
      path: '/applyout',
      name: 'Applyout',
      component: () => import("../components/admin/applyout.vue") //退租申请
    },
    {
      path: '/addhetong',
      name: 'Addhetong',
      component: () => import("../components/admin/addhetong.vue") // 添加合同
    },
    {
      path: '/edithetong',
      name: 'Edithetong',
      component: () => import("../components/admin/edithetong.vue") //修改合同
    },
    {
      path: '/wrong',
      name: 'Wrong',
      component: () => import("../components/admin/wrong.vue") //待处理报障
    },
    {
      path: '/solve',
      name: 'Solve',
      component: () => import("../components/admin/solve.vue") //已处理报障
    },
    {
      path: '/showddpaid',
      name: 'Showddpaid',
      component: () => import("../components/admin/showddpaid.vue") //我要收租
    }, {
      path: '/editschedule',
      name: 'Editschedule',
      component: () => import("../components/admin/editschedule.vue") //我要收租
    }, {
      path: '/yaomoney',
      name: 'Yaomoney',
      component: () => import("../components/admin/yaomoney.vue") //我要收租
    },
    {
      path: '/topaid',
      name: 'Topaid',
      component: () => import("../components/admin/topaid.vue") //租客代缴租金
    },
    {
      path: '/paid',
      name: 'Paid',
      component: () => import("../components/admin/paid.vue") //租客已缴租金
    },
    {
      path: '/schedule',
      name: 'Schedule',
      component: () => import("../components/admin/schedule.vue") //查看日程
    },
    {
      path: '/addschedule',
      name: 'Addschedule',
      component: () => import("../components/admin/addschedule.vue") //添加日程
    },
    {
      path: '/grxx',
      name: 'Grxx',
      component: () => import("../components/admin/grxx.vue") //
    },
    {
      path: '/admin',
      name: 'Admin',
      component: () => import("../components/admin/admin.vue") //租客已缴租金
    },
    {
      path: '/pass',
      name: 'Pass',
      component: () => import("../components/admin/pass.vue") //租客已缴租金
    }]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import("../components/login/Login.vue"),
  },
  {
    path: '/super',
    name: 'Super',
    component: () => import("../components/super/main.vue"),
  },
  {
    path: '/superlogin',
    name: 'Superlogin',
    component: () => import("../components/login/superLogin.vue"),
  },
  {
    path: '/u',
    component: umain,
    redirect: '/uindex',
    children: [{
      path: '/uindex',
      component: index
    },
    {
      path: '/uhouselist',
      name: 'uHouseList',
      component: () => import("../components/zuke/houseList.vue"),  //房屋列表
    },
    {
      path: '/uapplyhouse',
      name: 'uApplyhouse',
      component: () => import("../components/zuke/applyhouse.vue"),  //房屋列表
    },
    {
      path: '/uzulist',
      name: 'uZulist',
      component: () => import("../components/zuke/zulist.vue") //在租列表
    },
    {
      path: '/uhetong',
      name: 'uHetong',
      component: () => import("../components/zuke/hetong.vue") //查看合同
    },
    {
      path: '/ucheckout',
      name: 'uCheckout',
      component: () => import("../components/zuke/checkout.vue") //已退租列表
    },
    {
      path: '/uapplylist',
      name: 'uApplylist',
      component: () => import("../components/zuke/applylist.vue") //看房申请
    },
    {
      path: '/uapplyout',
      name: 'uApplyout',
      component: () => import("../components/zuke/applyout.vue") //退租申请
    },
    {
      path: '/uwrong',
      name: 'uWrong',
      component: () => import("../components/zuke/wrong.vue") //待处理报障
    },
    {
      path: '/addwrong',
      name: 'uAddwrong',
      component: () => import("../components/zuke/addwrong.vue") //我要报障
    },
    {
      path: '/addwronginfo',
      name: 'uAddwronginfo',
      component: () => import("../components/zuke/addwrongInfo.vue") //我要报障
    },
    {
      path: '/usolve',
      name: 'uSolve',
      component: () => import("../components/zuke/solve.vue") //已处理报障
    },
    {
      path: '/utopaid',
      name: 'uTopaid',
      component: () => import("../components/zuke/topaid.vue") //租客代缴租金
    },
    {
      path: '/upaid',
      name: 'uPaid',
      component: () => import("../components/zuke/paid.vue") //租客已缴租金
    },
    {
      path: '/user',
      name: 'User',
      component: () => import("../components/zuke/user.vue") //租客已缴租金
    },
    {
      path: '/ugrxx',
      name: 'uGrxx',
      component: () => import("../components/zuke/grxx.vue") //租客已缴租金
    },
    {
      path: '/upass',
      name: 'uPass',
      component: () => import("../components/zuke/pass.vue") //租客已缴租金
    }]
  },
  {
    path: '/:msg*',
    component: () => import("../components/404/404.vue"),
  }
  // { // 重定向404页面
  //   path: '/:catchAll(.*)',
  //   redirect: '/404'
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 挂载路由导航守卫  ——————————  登录验证
router.beforeEach((to, from, next) => {
  console.log(routes);
  console.log(to.path);
  console.log(from.path);

  // 获取token
  const token = window.sessionStorage.getItem('token')
  console.log("token==>" + token);

  if (to.path === '/login' || to.path === '/superlogin') {
    if (token) next(from.fullPath)
    next()
  }

  if (!token) next('/login')
  else next()
})
// 全局后置路由，简单写一个弹窗，在进入每一个路由组件后弹出一句话
// router.afterEach(() => {
//   alert('欢迎你！！');
// })

export default router
