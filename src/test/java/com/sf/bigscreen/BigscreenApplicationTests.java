package com.sf.bigscreen;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BigscreenApplicationTests {

    @Test
    void contextLoads() {
        String str = "[\\n\" +\n" +
                "                \"  {\\n\" +\n" +
                "                \"    path: '/permission',\\n\" +\n" +
                "                \"    component: Layout,\\n\" +\n" +
                "                \"    redirect: '/permission/page',\\n\" +\n" +
                "                \"    alwaysShow: true, // will always show the root menu\\n\" +\n" +
                "                \"    name: 'Permission',\\n\" +\n" +
                "                \"    meta: {\\n\" +\n" +
                "                \"      title: '大屏展示',\\n\" +\n" +
                "                \"      icon: 'lock',\\n\" +\n" +
                "                \"      roles: ['admin', 'editor'] // you can set roles in root nav\\n\" +\n" +
                "                \"    },\\n\" +\n" +
                "                \"    children: [\\n\" +\n" +
                "                \"      {\\n\" +\n" +
                "                \"        path: 'page',\\n\" +\n" +
                "                \"        component: () => import('@/views/permission/role'),\\n\" +\n" +
                "                \"        name: 'PagePermission',\\n\" +\n" +
                "                \"        meta: {\\n\" +\n" +
                "                \"          title: '一号大厅展示',\\n\" +\n" +
                "                \"          src: 'http://localhost:50401/analysis/dashboard/show/03590db461799c1f107b/',\\n\" +\n" +
                "                \"          roles: ['admin'] // or you can only set roles in sub nav\\n\" +\n" +
                "                \"        }\\n\" +\n" +
                "                \"      },\\n\" +\n" +
                "                \"      {\\n\" +\n" +
                "                \"        path: 'directive',\\n\" +\n" +
                "                \"        component: () => import('@/views/permission/role'),\\n\" +\n" +
                "                \"        name: 'DirectivePermission',\\n\" +\n" +
                "                \"        meta: {\\n\" +\n" +
                "                \"          title: '二号大厅展示',\\n\" +\n" +
                "                \"          src: 'http://localhost:50401/analysis/dashboard/show/05cd39547179a1a1b489/'\\n\" +\n" +
                "                \"          // if do not set roles, means: this page does not require permission\\n\" +\n" +
                "                \"        }\\n\" +\n" +
                "                \"      },\\n\" +\n" +
                "                \"      {\\n\" +\n" +
                "                \"        path: 'role',\\n\" +\n" +
                "                \"        component: () => import('@/views/permission/role'),\\n\" +\n" +
                "                \"        name: 'RolePermission',\\n\" +\n" +
                "                \"        meta: {\\n\" +\n" +
                "                \"          title: '三号大厅展示',\\n\" +\n" +
                "                \"          src: 'http://localhost:50401/analysis/dashboard/show/09d756e23179a12580fb/',\\n\" +\n" +
                "                \"          roles: ['admin']\\n\" +\n" +
                "                \"        }\\n\" +\n" +
                "                \"      }\\n\" +\n" +
                "                \"      , {\\n\" +\n" +
                "                \"        path: 'firstHall',\\n\" +\n" +
                "                \"        component: () => import('@/views/permission/role'),\\n\" +\n" +
                "                \"        name: 'carousel',\\n\" +\n" +
                "                \"        meta: {\\n\" +\n" +
                "                \"          src: 'http://localhost:50401/analysis/dashboard/show/03590db461799c1f107b/',\\n\" +\n" +
                "                \"          title: '四号大厅展示',\\n\" +\n" +
                "                \"          roles: ['admin']\\n\" +\n" +
                "                \"        }\\n\" +\n" +
                "                \"      }\\n\" +\n" +
                "                \"    ]\\n\" +\n" +
                "                \"  },\\n\" +\n" +
                "                \"  // 404 page must be placed at the end !!!\\n\" +\n" +
                "                \"  { path: '*', redirect: '/404', hidden: true }\\n\" +\n" +
                "                \"]";
        String s = str.replaceAll("\r|\n", "");
        System.out.println(s);
    }

}
